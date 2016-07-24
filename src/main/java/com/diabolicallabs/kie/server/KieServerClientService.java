package com.diabolicallabs.kie.server;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

@ProxyGen
@VertxGen
public interface KieServerClientService {

  static String DEFAULT_ADDRESS = "kie.server.client.service";

  static KieServerClientService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(KieServerClientService.class, vertx, address);
  }

  void information(Handler<AsyncResult<KieServerInformation>> handler);

  void createContainer(String name, GAV gav, Handler<AsyncResult<KieContainer>> handler);
  void container(String name, Handler<AsyncResult<KieContainer>> handler);
  void containers(Handler<AsyncResult<List<KieContainer>>> handler);
  void deleteContainer(String name, Handler<AsyncResult<Void>> handler);

  void processDefinitions(Handler<AsyncResult<List<KieProcessDefinition>>> handler);
  void processVariableDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler);
  void processServiceTaskDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler);
  void processUserTaskDefinitions(String containerName, String processId, Handler<AsyncResult<List<KieUserTaskDefinition>>> handler);
  void processSubprocessDefinitions(String containerName, String processId, Handler<AsyncResult<List<String>>> handler);
  void processEntityDefinitions(String containerName, String processId, Handler<AsyncResult<JsonObject>> handler);
  void processUserTaskInputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<JsonObject>> handler);
  void processUserTaskOutputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<JsonObject>> handler);

  void processInstances(Handler<AsyncResult<List<KieProcessInstance>>> handler);
  void processInstance(String containerName, Long processInstanceId, Handler<AsyncResult<KieProcessInstance>> handler);
  void startProcess(String containerName, String processId, JsonObject vars, Handler<AsyncResult<Long>> handler);
  void startProcessWithCorrelation(String containerName, String processId, String correlationId, JsonObject vars, Handler<AsyncResult<Long>> handler);
  void deleteProcessInstance(String containerName, Long processInstanceId, Handler<AsyncResult<Void>> handler);
  void processInstanceSignals(String containerName, Long processInstanceId, Handler<AsyncResult<List<String>>> handler);
  void processInstanceVariableValue(String containerName, Long processInstanceId, String variableName, Handler<AsyncResult<String>> handler);
  void processInstanceUpdateVariableValue(String containerName, Long processInstanceId, String variableName, String value, Handler<AsyncResult<Void>> handler);
  void processInstanceUpdateVariables(String containerName, Long processInstanceId, JsonObject variables, Handler<AsyncResult<Void>> handler);
  void processInstanceWorkItems(String containerName, Long processInstanceId, Handler<AsyncResult<List<KieWorkItemInstance>>> handler);
  void processInstanceWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<KieWorkItemInstance>> handler);
  void processInstanceAbortWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler);
  void processInstanceCompleteWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler);
  void processInstanceSignal(String containerName, Long processInstanceId, String signalName, JsonObject signalPayload, Handler<AsyncResult<Void>> handler);
  void processInstancesSignal(String containerName, String signalName, JsonObject signalPayload, Handler<AsyncResult<Void>> handler);

}
