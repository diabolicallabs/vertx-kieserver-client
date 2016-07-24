package com.diabolicallabs.kie.server;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.http.HttpClientResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KieServerClientServiceImpl implements KieServerClientService {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  String host;
  int port;
  String encodedCredential;
  String urlPrefix;
  Vertx vertx;

  public KieServerClientServiceImpl(Vertx vertx, String host, int port, String encodedCredential) {

    this.vertx = vertx;
    this.host = host;
    this.port = port;
    this.encodedCredential = encodedCredential;
    urlPrefix = "/kie-server/services/rest/";
  }

  @FunctionalInterface
  private interface SuccessHandler {
    <T> void handle(Buffer buffer, T resultHandler);
  }
  @FunctionalInterface
  private interface ResponseHandler {
    <T> void handle (HttpClientResponse response, Function<Buffer, T> successFunction, Handler<AsyncResult<T>> resultHandler);
  }

  private ResponseHandler successTagResponseHandler = new ResponseHandler() {
    @Override
    public <T> void handle(HttpClientResponse response, Function<Buffer, T> successFunction, Handler<AsyncResult<T>> resultHandler) {

      response.bodyHandler(buffer -> {

        JsonObject reply = buffer.toJsonObject();
        if (reply.getString("type").equals("SUCCESS")) {
          T result = successFunction.apply(buffer);
          if (result == null) {
            resultHandler.handle((Future.succeededFuture()));
          } else {
            resultHandler.handle((Future.succeededFuture(result)));
          }
        } else {
          resultHandler.handle(Future.failedFuture(reply.getString("msg")));
        }
      });
    }
  };

  private class CodeResponseHandler implements ResponseHandler {

    private int successCode;
    CodeResponseHandler(int successCode) {
      this.successCode = successCode;
    }

    @Override
    public <T> void handle(HttpClientResponse response, Function<Buffer, T> successFunction, Handler<AsyncResult<T>> resultHandler) {

      response.bodyHandler(buffer -> {
        logger.info("Code: " + response.statusCode() + " Message: " + response.statusMessage() + " Buffer: " + buffer.toString());
        if (response.statusCode() == successCode) {
          T result = successFunction.apply(buffer);
          if (result == null) {
            resultHandler.handle((Future.succeededFuture()));
          } else {
            resultHandler.handle((Future.succeededFuture(result)));
          }
        } else {
          resultHandler.handle(Future.failedFuture("Code: " + response.statusCode() + " Message: " + response.statusMessage() + " Buffer: " + buffer.toString()));
        }
      });
    }
  }

  private <T> void doDelete(String sUrl, ResponseHandler responseHandler, Function<Buffer, T> successFunction, Handler<AsyncResult<T>> resultHandler) {

    vertx
      .createHttpClient()
      .delete(port, host, urlPrefix + sUrl,
        response -> responseHandler.handle(response, successFunction, resultHandler)
      )
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();

  }

  private <T> void doGet(String sUrl, ResponseHandler responseHandler, Function<Buffer, T> successFunction, Handler<AsyncResult<T>> resultHandler) {

    String fullUrl = urlPrefix + sUrl;
    logger.info("About to GET from " + fullUrl);

    vertx
      .createHttpClient()
      .get(port, host, fullUrl,
        response -> responseHandler.handle(response, successFunction, resultHandler)
      )
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();

  }

  private <T> void doPost(String sUrl, JsonObject payload, ResponseHandler responseHandler, Function<Buffer, T> successFunction, Handler<AsyncResult<T>> resultHandler) {

    String fullUrl = urlPrefix + sUrl;
    logger.info("About to POST to " + fullUrl);

    vertx
      .createHttpClient()
      .post(port, host, fullUrl,
        response -> responseHandler.handle(response, successFunction, resultHandler)
      )
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(payload.encode());

  }

  private <T> void doPut(String sUrl, JsonObject payload, ResponseHandler responseHandler, Function<Buffer, T> successFunction, Handler<AsyncResult<T>> resultHandler) {

    String fullUrl = urlPrefix + sUrl;
    logger.info("About to PUT to " + fullUrl);

    vertx
      .createHttpClient()
      .put(port, host, fullUrl,
        response -> responseHandler.handle(response, successFunction, resultHandler)
      )
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(payload.encode());

  }


  @Override
  public void createContainer(String name, GAV gav, Handler<AsyncResult<KieContainer>> handler) {

    JsonObject payload = new JsonObject().put("release-id", gav.toJson());
    String sUrl = "server/containers/" + name;

    Function<Buffer, KieContainer> successFunction = buffer -> {
      JsonObject json = buffer.toJsonObject().getJsonObject("result").getJsonObject("kie-container");
      return new KieContainer(json);
    };

    doPut(sUrl, payload, successTagResponseHandler, successFunction, handler);
  }


  @Override
  public void deleteContainer(String name, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + name;

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doDelete(sUrl, successTagResponseHandler, successFunction, handler);

  }

  @Override
  public void processDefinitions(Handler<AsyncResult<List<KieProcessDefinition>>> handler) {

    String sUrl = "server/queries/processes/definitions";

    Function<Buffer, List<KieProcessDefinition>> successFunction = buffer -> {
      JsonObject response = buffer.toJsonObject();
      List<KieProcessDefinition> list = response.getJsonArray("processes").stream()
        .map(json -> (JsonObject)json)
        .map(KieProcessDefinition::new)
        .collect(Collectors.toList());

      return list;
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

  }

  @Override
  public void processInstances(Handler<AsyncResult<List<KieProcessInstance>>> handler) {

    String sUrl = "server/queries/processes/instances/";

    Function<Buffer, List<KieProcessInstance>> successFunction = buffer -> {
      JsonObject response = buffer.toJsonObject();
      List<KieProcessInstance> list = response.getJsonArray("process-instance").stream()
        .map(json -> (JsonObject)json)
        .map(KieProcessInstance::new)
        .collect(Collectors.toList());

      return list;
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);
  }

  @Override
  public void processInstance(String containerName, Long processInstanceId, Handler<AsyncResult<KieProcessInstance>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "?withVars=true";

    Function<Buffer, KieProcessInstance> successFunction = buffer -> {
      JsonObject response = buffer.toJsonObject();
      return new KieProcessInstance(response);
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

  }

  @Override
  public void processVariableDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/variables/";

    Function<Buffer, JsonObject> successFunction = buffer -> {
      return buffer.toJsonObject().getJsonObject("variables");
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/definitions/" + processId + "/variables/",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              handler.handle(Future.succeededFuture(buffer.toJsonObject().getJsonObject("variables")));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processServiceTaskDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/service";

    Function<Buffer, JsonObject> successFunction = buffer -> {
      return buffer.toJsonObject().getJsonObject("tasks");
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/service",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              handler.handle(Future.succeededFuture(buffer.toJsonObject().getJsonObject("tasks")));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processUserTaskDefinitions(String containerName, String processId, Handler<AsyncResult<List<KieUserTaskDefinition>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users";

    Function<Buffer, List<KieUserTaskDefinition>> successFunction = buffer -> {
      List<KieUserTaskDefinition> list = buffer.toJsonObject().getJsonArray("task").stream()
        .map(json -> (JsonObject)json)
        .map(KieUserTaskDefinition::new)
        .collect(Collectors.toList());
      return list;
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("Buffer: " + buffer.toString());
              List<KieUserTaskDefinition> list = buffer.toJsonObject().getJsonArray("task").stream()
                .map(json -> (JsonObject)json)
                .map(KieUserTaskDefinition::new)
                .collect(Collectors.toList());
              handler.handle(Future.succeededFuture(list));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processSubprocessDefinitions(String containerName, String processId, Handler<AsyncResult<List<String>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/subprocesses";

    Function<Buffer, List<String>> successFunction = buffer -> {
      List<String> list = buffer.toJsonObject().getJsonArray("subprocesses").stream()
        .map(name -> (String)name)
        .collect(Collectors.toList());
      return list;
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/definitions/" + processId + "/subprocesses",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("Buffer: " + buffer.toString());
              List<String> list = buffer.toJsonObject().getJsonArray("subprocesses").stream()
                .map(name -> (String)name)
                .collect(Collectors.toList());
              handler.handle(Future.succeededFuture(list));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processEntityDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/entities";

    Function<Buffer, JsonObject> successFunction = buffer -> {
      return buffer.toJsonObject().getJsonObject("associated-entities");
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/definitions/" + processId + "/entities",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("Buffer: " + buffer.toString());
              handler.handle(Future.succeededFuture(buffer.toJsonObject().getJsonObject("associated-entities")));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processUserTaskInputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users/" + taskName + "/inputs";

    Function<Buffer, JsonObject> successFunction = buffer -> {
      return buffer.toJsonObject().getJsonObject("inputs");
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    logger.info("User Task Input Request: " + sUrl);
    vertx
      .createHttpClient()
      .get(port, host, sUrl,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("Buffer: " + buffer.toString());
              handler.handle(Future.succeededFuture(buffer.toJsonObject().getJsonObject("inputs")));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processUserTaskOutputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users/" + taskName + "/outputs";

    Function<Buffer, JsonObject> successFunction = buffer -> {
      return buffer.toJsonObject().getJsonObject("outputs");
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users/" + taskName + "/outputs",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("Buffer: " + buffer.toString());
              handler.handle(Future.succeededFuture(buffer.toJsonObject().getJsonObject("outputs")));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void startProcess(String containerName, String processId, JsonObject vars, Handler<AsyncResult<Long>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/" + processId + "/instances/";

    Function<Buffer, Long> successFunction = buffer -> {
      return Long.valueOf(buffer.toString());
    };

    doPost(sUrl, vars, new CodeResponseHandler(201), successFunction, handler);
    /*
    vertx
      .createHttpClient()
      .post(port, host, urlPrefix + "server/containers/" + containerName + "/processes/" + processId + "/instances/",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 201) {
            responseHandler.bodyHandler(buffer -> {
              handler.handle(Future.succeededFuture(Long.valueOf(buffer.toString())));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(vars.encode());
      */
  }

  @Override
  public void startProcessWithCorrelation(String containerName, String processId, String correlationId, JsonObject vars, Handler<AsyncResult<Long>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/" + processId + "/instances/correlation/" + correlationId;

    Function<Buffer, Long> successFunction = buffer -> {
      return Long.valueOf(buffer.toString());
    };

    doPost(sUrl, vars, new CodeResponseHandler(201), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .post(port, host, urlPrefix + "server/containers/" + containerName + "/processes/" + processId + "/instances/correlation/" + correlationId,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 201) {
            responseHandler.bodyHandler(buffer -> {
              handler.handle(Future.succeededFuture(Long.valueOf(buffer.toString())));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(vars.encode());
      */
  }

  @Override
  public void deleteProcessInstance(String containerName, Long processInstanceId, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances?instanceId=" + processInstanceId;

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doDelete(sUrl, new CodeResponseHandler(204), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .delete(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances?instanceId=" + processInstanceId,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 204) {
            responseHandler.bodyHandler(buffer -> {
              handler.handle(Future.succeededFuture());
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processInstanceSignals(String containerName, Long processInstanceId, Handler<AsyncResult<List<String>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/signals";

    Function<Buffer, List<String>> successFunction = buffer -> {
      return buffer.toJsonArray().getList();
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/signals",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceSignals Result: " + buffer.toString());
              handler.handle(Future.succeededFuture(buffer.toJsonArray().getList()));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processInstanceVariableValue(String containerName, Long processInstanceId, String variableName, Handler<AsyncResult<String>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variable/" + variableName;

    Function<Buffer, String> successFunction = buffer -> {
      return buffer.toString();
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variable/" + variableName,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceVariableValue Result: " + buffer.toString());
              handler.handle(Future.succeededFuture(buffer.toString()));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processInstanceUpdateVariableValue(String containerName, Long processInstanceId, String variableName, String value, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variable/" + variableName;
    JsonObject payload = new JsonObject().put(variableName, value);

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doPut(sUrl, payload, new CodeResponseHandler(201), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .put(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variable/" + variableName,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 201) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceUpdateVariableValue Result: " + buffer.toString());
              handler.handle(Future.succeededFuture());
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(new JsonObject().put(variableName, value).encode());
      */
  }

  @Override
  public void processInstanceUpdateVariables(String containerName, Long processInstanceId, JsonObject variables, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variables";

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doPost(sUrl, variables, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .post(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variables",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceUpdateVariables Result: " + buffer.toString());
              handler.handle(Future.succeededFuture());
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(variables.encode());
      */
  }

  @Override
  public void processInstanceWorkItems(String containerName, Long processInstanceId, Handler<AsyncResult<List<KieWorkItemInstance>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems";

    Function<Buffer, List<KieWorkItemInstance>> successFunction = buffer -> {
      List<KieWorkItemInstance> list = buffer.toJsonObject().getJsonArray("work-item-instance").stream()
        .map(json -> (JsonObject)json)
        .map(KieWorkItemInstance::new)
        .collect(Collectors.toList());
      return list;
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceWorkItems Result: " + buffer.toString());
              List<KieWorkItemInstance> list = buffer.toJsonObject().getJsonArray("work-item-instance").stream()
                .map(json -> (JsonObject)json)
                .map(KieWorkItemInstance::new)
                .collect(Collectors.toList());
              handler.handle(Future.succeededFuture(list));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processInstanceWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<KieWorkItemInstance>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId;

    Function<Buffer, KieWorkItemInstance> successFunction = buffer -> {
      return new KieWorkItemInstance(buffer.toJsonObject());
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceWorkItem Result: " + buffer.toString());
              handler.handle(Future.succeededFuture(new KieWorkItemInstance(buffer.toJsonObject())));
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processInstanceAbortWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId + "/aborted";

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doPut(sUrl, new JsonObject(), new CodeResponseHandler(201), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .put(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId + "/aborted",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 201) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceAbortWorkItem Result: " + buffer.toString());
              handler.handle(Future.succeededFuture());
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processInstanceCompleteWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId + "/completed";

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doPut(sUrl, new JsonObject(), new CodeResponseHandler(201), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .put(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId + "/completed",
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 201) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceCompleteWorkItem Result: " + buffer.toString());
              handler.handle(Future.succeededFuture());
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void processInstanceSignal(String containerName, Long processInstanceId, String signalName, JsonObject signalPayload, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/signal/" + signalName;

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doPost(sUrl, signalPayload, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .post(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/signal/" + signalName,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstanceSignal Result: " + buffer.toString());
              handler.handle(Future.succeededFuture());
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(signalPayload.encode());
      */
  }

  @Override
  public void processInstancesSignal(String containerName, String signalName, JsonObject signalPayload, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/signal/" + signalName;

    Function<Buffer, Void> successFunction = buffer -> {
      return null;
    };

    doPost(sUrl, signalPayload, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .post(port, host, urlPrefix + "server/containers/" + containerName + "/processes/instances/signal/" + signalName,
        responseHandler -> {
          System.out.println("Code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage());
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(buffer -> {
              logger.info("processInstancesSignal Result: " + buffer.toString());
              handler.handle(Future.succeededFuture());
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end(signalPayload.encode());
      */
  }

  @Override
  public void container(String name, Handler<AsyncResult<KieContainer>> handler) {

    String sUrl = "server/containers/" + name;

    Function<Buffer, KieContainer> successFunction = buffer -> {
      return new KieContainer(buffer.toJsonObject());
    };

    doGet(sUrl, successTagResponseHandler, successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers/" + name,
        responseHandler -> {
          responseHandler.bodyHandler(bodyHandler -> {
            JsonObject response = bodyHandler.toJsonObject();
            logger.debug("Response: " + response.encodePrettily());
            if (response.getString("type").equals("SUCCESS")) {
              JsonObject json = response.getJsonObject("result").getJsonObject("kie-container");
              handler.handle(Future.succeededFuture(new KieContainer(json)));
            } else {
              handler.handle(Future.failedFuture(response.getString("msg")));
            }
          });
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
      */
  }

  @Override
  public void information(Handler<AsyncResult<KieServerInformation>> handler) {

    String sUrl = "server";

    Function<Buffer, KieServerInformation> successFunction = buffer -> {
      return new KieServerInformation(buffer.toJsonObject());
    };

    doGet(sUrl, new CodeResponseHandler(200), successFunction, handler);

    /*
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server",
        responseHandler -> {
          if (responseHandler.statusCode() == 200) {
            responseHandler.bodyHandler(bodyHandler -> {
              JsonObject response = bodyHandler.toJsonObject();
              if (response.getString("type").equals("SUCCESS")) {
                JsonObject json = response.getJsonObject("result").getJsonObject("kie-server-info");
                handler.handle(Future.succeededFuture(new KieServerInformation(json)));
              } else {
                handler.handle(Future.failedFuture(response.getString("msg")));
              }
            });
          } else {
            handler.handle(Future.failedFuture(responseHandler.statusMessage()));
          }
        })
      .putHeader("accept", "application/json").putHeader("Authorization", "Basic " + encodedCredential).end();
      */
  }


  @Override
  public void containers(Handler<AsyncResult<List<KieContainer>>> handler) {

    String sUrl = "server/containers";

    Function<Buffer, List<KieContainer>> successFunction = buffer -> {
      JsonArray containers = buffer.toJsonObject().getJsonObject("result").getJsonObject("kie-containers").getJsonArray("kie-container");
      List<KieContainer> list = containers.stream()
        .map(json -> (JsonObject)json)
        .map(KieContainer::new)
        .collect(Collectors.toList());
      return list;
    };

    doGet(sUrl, successTagResponseHandler, successFunction, handler);

    /*
    logger.info("About to get all containers");
    vertx
      .createHttpClient()
      .get(port, host, urlPrefix + "server/containers",
        responseHandler -> {
          responseHandler.bodyHandler(bodyHandler -> {
            JsonObject response = bodyHandler.toJsonObject();
            logger.info("containers Response: " + response.encodePrettily());
            if (response.getString("type").equals("SUCCESS")) {
              JsonArray containers = response.getJsonObject("result").getJsonObject("kie-containers").getJsonArray("kie-container");
              List<KieContainer> list = containers.stream()
                .map(json -> (JsonObject)json)
                .map(KieContainer::new)
                .collect(Collectors.toList());
              handler.handle(Future.succeededFuture(list));
            } else {
              handler.handle(Future.failedFuture(response.getString("msg")));
            }
          });
        })
      .putHeader("accept", "application/json").putHeader("Authorization", "Basic " + encodedCredential).end();
      */

  }

}
