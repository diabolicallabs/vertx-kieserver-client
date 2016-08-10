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

/** @module kieserver-js/kie_server_client_service */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JKieServerClientService = com.diabolicallabs.kie.server.service.KieServerClientService;
var KieServerInformation = com.diabolicallabs.kie.server.model.KieServerInformation;
var KieProcessInstance = com.diabolicallabs.kie.server.model.KieProcessInstance;
var KieProcessDefinition = com.diabolicallabs.kie.server.model.KieProcessDefinition;
var KieWorkItemInstance = com.diabolicallabs.kie.server.model.KieWorkItemInstance;
var KieContainer = com.diabolicallabs.kie.server.model.KieContainer;
var GAV = com.diabolicallabs.kie.server.model.GAV;
var KieUserTaskDefinition = com.diabolicallabs.kie.server.model.KieUserTaskDefinition;

/**
 @class
*/
var KieServerClientService = function(j_val) {

  var j_kieServerClientService = j_val;
  var that = this;

  /**

   @public
   @param handler {function} 
   */
  this.information = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_kieServerClientService["information(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param name {string} 
   @param gav {Object} 
   @param handler {function} 
   */
  this.createContainer = function(name, gav, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && (typeof __args[1] === 'object' && __args[1] != null) && typeof __args[2] === 'function') {
      j_kieServerClientService["createContainer(java.lang.String,com.diabolicallabs.kie.server.model.GAV,io.vertx.core.Handler)"](name, gav != null ? new GAV(new JsonObject(JSON.stringify(gav))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param name {string} 
   @param handler {function} 
   */
  this.container = function(name, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_kieServerClientService["container(java.lang.String,io.vertx.core.Handler)"](name, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   */
  this.containers = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_kieServerClientService["containers(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param name {string} 
   @param handler {function} 
   */
  this.deleteContainer = function(name, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_kieServerClientService["deleteContainer(java.lang.String,io.vertx.core.Handler)"](name, function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   */
  this.processDefinitions = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_kieServerClientService["processDefinitions(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param handler {function} 
   */
  this.processVariableDefinitions = function(containerName, processId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_kieServerClientService["processVariableDefinitions(java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, processId, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param handler {function} 
   */
  this.processServiceTaskDefinitions = function(containerName, processId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_kieServerClientService["processServiceTaskDefinitions(java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, processId, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param handler {function} 
   */
  this.processUserTaskDefinitions = function(containerName, processId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_kieServerClientService["processUserTaskDefinitions(java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, processId, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param handler {function} 
   */
  this.processSubprocessDefinitions = function(containerName, processId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_kieServerClientService["processSubprocessDefinitions(java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, processId, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param handler {function} 
   */
  this.processEntityDefinitions = function(containerName, processId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_kieServerClientService["processEntityDefinitions(java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, processId, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param taskName {string} 
   @param handler {function} 
   */
  this.processUserTaskInputDefinitions = function(containerName, processId, taskName, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'string' && typeof __args[3] === 'function') {
      j_kieServerClientService["processUserTaskInputDefinitions(java.lang.String,java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, processId, taskName, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param taskName {string} 
   @param handler {function} 
   */
  this.processUserTaskOutputDefinitions = function(containerName, processId, taskName, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'string' && typeof __args[3] === 'function') {
      j_kieServerClientService["processUserTaskOutputDefinitions(java.lang.String,java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, processId, taskName, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param handler {function} 
   */
  this.processInstances = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_kieServerClientService["processInstances(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param handler {function} 
   */
  this.processInstance = function(containerName, processInstanceId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] === 'function') {
      j_kieServerClientService["processInstance(java.lang.String,java.lang.Long,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param vars {Object} 
   @param handler {function} 
   */
  this.startProcess = function(containerName, processId, vars, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_kieServerClientService["startProcess(java.lang.String,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](containerName, processId, utils.convParamJsonObject(vars), function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnLong(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processId {string} 
   @param correlationId {string} 
   @param vars {Object} 
   @param handler {function} 
   */
  this.startProcessWithCorrelation = function(containerName, processId, correlationId, vars, handler) {
    var __args = arguments;
    if (__args.length === 5 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] === 'string' && (typeof __args[3] === 'object' && __args[3] != null) && typeof __args[4] === 'function') {
      j_kieServerClientService["startProcessWithCorrelation(java.lang.String,java.lang.String,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](containerName, processId, correlationId, utils.convParamJsonObject(vars), function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnLong(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param handler {function} 
   */
  this.deleteProcessInstance = function(containerName, processInstanceId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] === 'function') {
      j_kieServerClientService["deleteProcessInstance(java.lang.String,java.lang.Long,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param handler {function} 
   */
  this.processInstanceSignals = function(containerName, processInstanceId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] === 'function') {
      j_kieServerClientService["processInstanceSignals(java.lang.String,java.lang.Long,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param variableName {string} 
   @param handler {function} 
   */
  this.processInstanceVariableValue = function(containerName, processInstanceId, variableName, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] === 'string' && typeof __args[3] === 'function') {
      j_kieServerClientService["processInstanceVariableValue(java.lang.String,java.lang.Long,java.lang.String,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), variableName, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param variableName {string} 
   @param value {string} 
   @param handler {function} 
   */
  this.processInstanceUpdateVariableValue = function(containerName, processInstanceId, variableName, value, handler) {
    var __args = arguments;
    if (__args.length === 5 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] === 'string' && typeof __args[3] === 'string' && typeof __args[4] === 'function') {
      j_kieServerClientService["processInstanceUpdateVariableValue(java.lang.String,java.lang.Long,java.lang.String,java.lang.String,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), variableName, value, function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param variables {Object} 
   @param handler {function} 
   */
  this.processInstanceUpdateVariables = function(containerName, processInstanceId, variables, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_kieServerClientService["processInstanceUpdateVariables(java.lang.String,java.lang.Long,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), utils.convParamJsonObject(variables), function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param handler {function} 
   */
  this.processInstanceWorkItems = function(containerName, processInstanceId, handler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] === 'function') {
      j_kieServerClientService["processInstanceWorkItems(java.lang.String,java.lang.Long,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param workItemId {number} 
   @param handler {function} 
   */
  this.processInstanceWorkItem = function(containerName, processInstanceId, workItemId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientService["processInstanceWorkItem(java.lang.String,java.lang.Long,java.lang.Long,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), utils.convParamLong(workItemId), function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param workItemId {number} 
   @param handler {function} 
   */
  this.processInstanceAbortWorkItem = function(containerName, processInstanceId, workItemId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientService["processInstanceAbortWorkItem(java.lang.String,java.lang.Long,java.lang.Long,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), utils.convParamLong(workItemId), function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param workItemId {number} 
   @param handler {function} 
   */
  this.processInstanceCompleteWorkItem = function(containerName, processInstanceId, workItemId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientService["processInstanceCompleteWorkItem(java.lang.String,java.lang.Long,java.lang.Long,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), utils.convParamLong(workItemId), function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param processInstanceId {number} 
   @param signalName {string} 
   @param signalPayload {Object} 
   @param handler {function} 
   */
  this.processInstanceSignal = function(containerName, processInstanceId, signalName, signalPayload, handler) {
    var __args = arguments;
    if (__args.length === 5 && typeof __args[0] === 'string' && typeof __args[1] ==='number' && typeof __args[2] === 'string' && (typeof __args[3] === 'object' && __args[3] != null) && typeof __args[4] === 'function') {
      j_kieServerClientService["processInstanceSignal(java.lang.String,java.lang.Long,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](containerName, utils.convParamLong(processInstanceId), signalName, utils.convParamJsonObject(signalPayload), function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param containerName {string} 
   @param signalName {string} 
   @param signalPayload {Object} 
   @param handler {function} 
   */
  this.processInstancesSignal = function(containerName, signalName, signalPayload, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
      j_kieServerClientService["processInstancesSignal(java.lang.String,java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](containerName, signalName, utils.convParamJsonObject(signalPayload), function(ar) {
      if (ar.succeeded()) {
        handler(null, null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_kieServerClientService;
};

/**

 @memberof module:kieserver-js/kie_server_client_service
 @param vertx {Vertx} 
 @param address {string} 
 @return {KieServerClientService}
 */
KieServerClientService.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JKieServerClientService["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), KieServerClientService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = KieServerClientService;