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

  private <T> void doDelete(String sUrl, ResponseHandler responseHandler, Handler<AsyncResult<T>> resultHandler, Function<Buffer, T> successFunction) {

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

  private <T> void doGet(String sUrl, ResponseHandler responseHandler, Handler<AsyncResult<T>> resultHandler, Function<Buffer, T> successFunction) {

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

  private <T> void doPost(String sUrl, JsonObject payload, ResponseHandler responseHandler, Handler<AsyncResult<T>> resultHandler, Function<Buffer, T> successFunction) {

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

  private <T> void doPut(String sUrl, JsonObject payload, ResponseHandler responseHandler, Handler<AsyncResult<T>> resultHandler, Function<Buffer, T> successFunction) {

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

    doPut(sUrl, payload, successTagResponseHandler, handler, buffer -> {
      JsonObject json = buffer.toJsonObject().getJsonObject("result").getJsonObject("kie-container");
      return new KieContainer(json);
    });
  }


  @Override
  public void deleteContainer(String name, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + name;

    doDelete(sUrl, successTagResponseHandler, handler, buffer -> null);

  }

  @Override
  public void processDefinitions(Handler<AsyncResult<List<KieProcessDefinition>>> handler) {

    String sUrl = "server/queries/processes/definitions";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer ->
      buffer.toJsonObject().getJsonArray("processes").stream()
        .map(json -> (JsonObject)json)
        .map(KieProcessDefinition::new)
        .collect(Collectors.toList()));
  }

  @Override
  public void processInstances(Handler<AsyncResult<List<KieProcessInstance>>> handler) {

    String sUrl = "server/queries/processes/instances/";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer ->
      buffer.toJsonObject().getJsonArray("process-instance").stream()
        .map(json -> (JsonObject)json)
        .map(KieProcessInstance::new)
        .collect(Collectors.toList()));
  }

  @Override
  public void processInstance(String containerName, Long processInstanceId, Handler<AsyncResult<KieProcessInstance>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "?withVars=true";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> new KieProcessInstance(buffer.toJsonObject()));
  }

  @Override
  public void processVariableDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/variables/";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> buffer.toJsonObject().getJsonObject("variables"));

  }

  @Override
  public void processServiceTaskDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/service";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> buffer.toJsonObject().getJsonObject("tasks"));

  }

  @Override
  public void processUserTaskDefinitions(String containerName, String processId, Handler<AsyncResult<List<KieUserTaskDefinition>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer ->
      buffer.toJsonObject().getJsonArray("task").stream()
        .map(json -> (JsonObject)json)
        .map(KieUserTaskDefinition::new)
        .collect(Collectors.toList()));

  }

  @Override
  public void processSubprocessDefinitions(String containerName, String processId, Handler<AsyncResult<List<String>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/subprocesses";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer ->
      buffer.toJsonObject().getJsonArray("subprocesses").stream()
        .map(name -> (String)name)
        .collect(Collectors.toList()));

  }

  @Override
  public void processEntityDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/entities";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> buffer.toJsonObject().getJsonObject("associated-entities"));

  }

  @Override
  public void processUserTaskInputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users/" + taskName + "/inputs";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> buffer.toJsonObject().getJsonObject("inputs"));

  }

  @Override
  public void processUserTaskOutputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<JsonObject>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/definitions/" + processId + "/tasks/users/" + taskName + "/outputs";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> buffer.toJsonObject().getJsonObject("outputs"));

  }

  @Override
  public void startProcess(String containerName, String processId, JsonObject vars, Handler<AsyncResult<Long>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/" + processId + "/instances/";

    doPost(sUrl, vars, new CodeResponseHandler(201), handler, buffer -> Long.valueOf(buffer.toString()));
  }

  @Override
  public void startProcessWithCorrelation(String containerName, String processId, String correlationId, JsonObject vars, Handler<AsyncResult<Long>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/" + processId + "/instances/correlation/" + correlationId;

    doPost(sUrl, vars, new CodeResponseHandler(201), handler, buffer -> Long.valueOf(buffer.toString()));

  }

  @Override
  public void deleteProcessInstance(String containerName, Long processInstanceId, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances?instanceId=" + processInstanceId;

    doDelete(sUrl, new CodeResponseHandler(204), handler, buffer -> null);

  }

  @Override
  public void processInstanceSignals(String containerName, Long processInstanceId, Handler<AsyncResult<List<String>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/signals";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> buffer.toJsonArray().getList());

  }

  @Override
  public void processInstanceVariableValue(String containerName, Long processInstanceId, String variableName, Handler<AsyncResult<String>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variable/" + variableName;

    doGet(sUrl, new CodeResponseHandler(200), handler, Buffer::toString);

  }

  @Override
  public void processInstanceUpdateVariableValue(String containerName, Long processInstanceId, String variableName, String value, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variable/" + variableName;
    JsonObject payload = new JsonObject().put(variableName, value);

    doPut(sUrl, payload, new CodeResponseHandler(201), handler, buffer -> null);

  }

  @Override
  public void processInstanceUpdateVariables(String containerName, Long processInstanceId, JsonObject variables, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/variables";

    doPost(sUrl, variables, new CodeResponseHandler(200), handler, buffer -> null);

  }

  @Override
  public void processInstanceWorkItems(String containerName, Long processInstanceId, Handler<AsyncResult<List<KieWorkItemInstance>>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer ->
      buffer.toJsonObject().getJsonArray("work-item-instance").stream()
        .map(json -> (JsonObject)json)
        .map(KieWorkItemInstance::new)
        .collect(Collectors.toList()));

  }

  @Override
  public void processInstanceWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<KieWorkItemInstance>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId;

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> new KieWorkItemInstance(buffer.toJsonObject()));

  }

  @Override
  public void processInstanceAbortWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId + "/aborted";

    doPut(sUrl, new JsonObject(), new CodeResponseHandler(201), handler, buffer -> null);

  }

  @Override
  public void processInstanceCompleteWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/workitems/" + workItemId + "/completed";

    doPut(sUrl, new JsonObject(), new CodeResponseHandler(201), handler, buffer -> null);

  }

  @Override
  public void processInstanceSignal(String containerName, Long processInstanceId, String signalName, JsonObject signalPayload, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/" + processInstanceId + "/signal/" + signalName;

    doPost(sUrl, signalPayload, new CodeResponseHandler(200), handler, buffer -> null);

  }

  @Override
  public void processInstancesSignal(String containerName, String signalName, JsonObject signalPayload, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/processes/instances/signal/" + signalName;

    doPost(sUrl, signalPayload, new CodeResponseHandler(200), handler, buffer -> null);

  }

  @Override
  public void container(String name, Handler<AsyncResult<KieContainer>> handler) {

    String sUrl = "server/containers/" + name;

    doGet(sUrl, successTagResponseHandler, handler, buffer -> new KieContainer(buffer.toJsonObject()));

  }

  @Override
  public void information(Handler<AsyncResult<KieServerInformation>> handler) {

    String sUrl = "server";

    doGet(sUrl, new CodeResponseHandler(200), handler, buffer -> new KieServerInformation(buffer.toJsonObject()));

  }

  @Override
  public void containers(Handler<AsyncResult<List<KieContainer>>> handler) {

    String sUrl = "server/containers";

    doGet(sUrl, successTagResponseHandler, handler, buffer -> {
      JsonArray containers = buffer.toJsonObject().getJsonObject("result").getJsonObject("kie-containers").getJsonArray("kie-container");
      return containers.stream()
        .map(json -> (JsonObject)json)
        .map(KieContainer::new)
        .collect(Collectors.toList());
    });

  }

}
