/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.diabolicallabs.kie.server.groovy.service;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import com.diabolicallabs.kie.server.model.KieProcessInstance
import com.diabolicallabs.kie.server.model.KieProcessDefinition
import io.vertx.groovy.core.Vertx
import com.diabolicallabs.kie.server.model.GAV
import java.util.List
import com.diabolicallabs.kie.server.model.KieServerInformation
import io.vertx.core.json.JsonObject
import com.diabolicallabs.kie.server.model.KieWorkItemInstance
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import com.diabolicallabs.kie.server.model.KieContainer
import com.diabolicallabs.kie.server.model.KieUserTaskDefinition
@CompileStatic
public class KieServerClientService {
  private final def com.diabolicallabs.kie.server.service.KieServerClientService delegate;
  public KieServerClientService(Object delegate) {
    this.delegate = (com.diabolicallabs.kie.server.service.KieServerClientService) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static KieServerClientService createProxy(Vertx vertx, String address) {
    def ret = InternalHelper.safeCreate(com.diabolicallabs.kie.server.service.KieServerClientService.createProxy(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, address), com.diabolicallabs.kie.server.groovy.service.KieServerClientService.class);
    return ret;
  }
  public void information(Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.information(handler != null ? new Handler<AsyncResult<com.diabolicallabs.kie.server.model.KieServerInformation>>() {
      public void handle(AsyncResult<com.diabolicallabs.kie.server.model.KieServerInformation> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result()?.toJson())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void createContainer(String name, Map<String, Object> gav, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.createContainer(name, gav != null ? new com.diabolicallabs.kie.server.model.GAV(io.vertx.lang.groovy.InternalHelper.toJsonObject(gav)) : null, handler != null ? new Handler<AsyncResult<com.diabolicallabs.kie.server.model.KieContainer>>() {
      public void handle(AsyncResult<com.diabolicallabs.kie.server.model.KieContainer> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result()?.toJson())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void container(String name, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.container(name, handler != null ? new Handler<AsyncResult<com.diabolicallabs.kie.server.model.KieContainer>>() {
      public void handle(AsyncResult<com.diabolicallabs.kie.server.model.KieContainer> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result()?.toJson())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void containers(Handler<AsyncResult<List<Map<String, Object>>>> handler) {
    delegate.containers(handler != null ? new Handler<AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieContainer>>>() {
      public void handle(AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieContainer>> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((List)ar.result()?.collect({(Map<String, Object>)InternalHelper.wrapObject(it?.toJson())})));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void deleteContainer(String name, Handler<AsyncResult<Void>> handler) {
    delegate.deleteContainer(name, handler);
  }
  public void processDefinitions(Handler<AsyncResult<List<Map<String, Object>>>> handler) {
    delegate.processDefinitions(handler != null ? new Handler<AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieProcessDefinition>>>() {
      public void handle(AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieProcessDefinition>> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((List)ar.result()?.collect({(Map<String, Object>)InternalHelper.wrapObject(it?.toJson())})));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processVariableDefinitions(String containerName, String processId, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.processVariableDefinitions(containerName, processId, handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonObject> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processServiceTaskDefinitions(String containerName, String processId, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.processServiceTaskDefinitions(containerName, processId, handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonObject> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processUserTaskDefinitions(String containerName, String processId, Handler<AsyncResult<List<Map<String, Object>>>> handler) {
    delegate.processUserTaskDefinitions(containerName, processId, handler != null ? new Handler<AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieUserTaskDefinition>>>() {
      public void handle(AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieUserTaskDefinition>> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((List)ar.result()?.collect({(Map<String, Object>)InternalHelper.wrapObject(it?.toJson())})));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processSubprocessDefinitions(String containerName, String processId, Handler<AsyncResult<List<String>>> handler) {
    delegate.processSubprocessDefinitions(containerName, processId, handler != null ? new Handler<AsyncResult<java.util.List<java.lang.String>>>() {
      public void handle(AsyncResult<java.util.List<java.lang.String>> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(ar.result()));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processEntityDefinitions(String containerName, String processId, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.processEntityDefinitions(containerName, processId, handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonObject> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processUserTaskInputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.processUserTaskInputDefinitions(containerName, processId, taskName, handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonObject> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processUserTaskOutputDefinitions(String containerName, String processId, String taskName, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.processUserTaskOutputDefinitions(containerName, processId, taskName, handler != null ? new Handler<AsyncResult<io.vertx.core.json.JsonObject>>() {
      public void handle(AsyncResult<io.vertx.core.json.JsonObject> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processInstances(Handler<AsyncResult<List<Map<String, Object>>>> handler) {
    delegate.processInstances(handler != null ? new Handler<AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieProcessInstance>>>() {
      public void handle(AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieProcessInstance>> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((List)ar.result()?.collect({(Map<String, Object>)InternalHelper.wrapObject(it?.toJson())})));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processInstance(String containerName, Long processInstanceId, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.processInstance(containerName, processInstanceId, handler != null ? new Handler<AsyncResult<com.diabolicallabs.kie.server.model.KieProcessInstance>>() {
      public void handle(AsyncResult<com.diabolicallabs.kie.server.model.KieProcessInstance> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result()?.toJson())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void startProcess(String containerName, String processId, Map<String, Object> vars, Handler<AsyncResult<Long>> handler) {
    delegate.startProcess(containerName, processId, vars != null ? new io.vertx.core.json.JsonObject(vars) : null, handler);
  }
  public void startProcessWithCorrelation(String containerName, String processId, String correlationId, Map<String, Object> vars, Handler<AsyncResult<Long>> handler) {
    delegate.startProcessWithCorrelation(containerName, processId, correlationId, vars != null ? new io.vertx.core.json.JsonObject(vars) : null, handler);
  }
  public void deleteProcessInstance(String containerName, Long processInstanceId, Handler<AsyncResult<Void>> handler) {
    delegate.deleteProcessInstance(containerName, processInstanceId, handler);
  }
  public void processInstanceSignals(String containerName, Long processInstanceId, Handler<AsyncResult<List<String>>> handler) {
    delegate.processInstanceSignals(containerName, processInstanceId, handler != null ? new Handler<AsyncResult<java.util.List<java.lang.String>>>() {
      public void handle(AsyncResult<java.util.List<java.lang.String>> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(ar.result()));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processInstanceVariableValue(String containerName, Long processInstanceId, String variableName, Handler<AsyncResult<String>> handler) {
    delegate.processInstanceVariableValue(containerName, processInstanceId, variableName, handler);
  }
  public void processInstanceUpdateVariableValue(String containerName, Long processInstanceId, String variableName, String value, Handler<AsyncResult<Void>> handler) {
    delegate.processInstanceUpdateVariableValue(containerName, processInstanceId, variableName, value, handler);
  }
  public void processInstanceUpdateVariables(String containerName, Long processInstanceId, Map<String, Object> variables, Handler<AsyncResult<Void>> handler) {
    delegate.processInstanceUpdateVariables(containerName, processInstanceId, variables != null ? new io.vertx.core.json.JsonObject(variables) : null, handler);
  }
  public void processInstanceWorkItems(String containerName, Long processInstanceId, Handler<AsyncResult<List<Map<String, Object>>>> handler) {
    delegate.processInstanceWorkItems(containerName, processInstanceId, handler != null ? new Handler<AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieWorkItemInstance>>>() {
      public void handle(AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieWorkItemInstance>> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((List)ar.result()?.collect({(Map<String, Object>)InternalHelper.wrapObject(it?.toJson())})));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processInstanceWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Map<String, Object>>> handler) {
    delegate.processInstanceWorkItem(containerName, processInstanceId, workItemId, handler != null ? new Handler<AsyncResult<com.diabolicallabs.kie.server.model.KieWorkItemInstance>>() {
      public void handle(AsyncResult<com.diabolicallabs.kie.server.model.KieWorkItemInstance> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture((Map<String, Object>)InternalHelper.wrapObject(ar.result()?.toJson())));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
  }
  public void processInstanceAbortWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler) {
    delegate.processInstanceAbortWorkItem(containerName, processInstanceId, workItemId, handler);
  }
  public void processInstanceCompleteWorkItem(String containerName, Long processInstanceId, Long workItemId, Handler<AsyncResult<Void>> handler) {
    delegate.processInstanceCompleteWorkItem(containerName, processInstanceId, workItemId, handler);
  }
  public void processInstanceSignal(String containerName, Long processInstanceId, String signalName, Map<String, Object> signalPayload, Handler<AsyncResult<Void>> handler) {
    delegate.processInstanceSignal(containerName, processInstanceId, signalName, signalPayload != null ? new io.vertx.core.json.JsonObject(signalPayload) : null, handler);
  }
  public void processInstancesSignal(String containerName, String signalName, Map<String, Object> signalPayload, Handler<AsyncResult<Void>> handler) {
    delegate.processInstancesSignal(containerName, signalName, signalPayload != null ? new io.vertx.core.json.JsonObject(signalPayload) : null, handler);
  }
}
