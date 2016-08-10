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

/** @module kieserver-js/kie_server_client_task_service */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JKieServerClientTaskService = com.diabolicallabs.kie.server.service.KieServerClientTaskService;

/**
 @class
*/
var KieServerClientTaskService = function(j_val) {

  var j_kieServerClientTaskService = j_val;
  var that = this;

  /**

   @public
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskActivate = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskActivate(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskClaim = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskClaim(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskStart = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskStart(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskStop = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskStop(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param variables {Object} 
   @param handler {function} 
   */
  this.taskComplete = function(encodedCredential, containerName, taskId, variables, handler) {
    var __args = arguments;
    if (__args.length === 5 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && (typeof __args[3] === 'object' && __args[3] != null) && typeof __args[4] === 'function') {
      j_kieServerClientTaskService["taskComplete(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), utils.convParamJsonObject(variables), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param delegateId {string} 
   @param handler {function} 
   */
  this.taskDelegate = function(encodedCredential, containerName, taskId, delegateId, handler) {
    var __args = arguments;
    if (__args.length === 5 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'string' && typeof __args[4] === 'function') {
      j_kieServerClientTaskService["taskDelegate(java.lang.String,java.lang.String,java.lang.Long,java.lang.String,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), delegateId, function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskExit = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskExit(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskFail = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskFail(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param userId {string} 
   @param handler {function} 
   */
  this.taskForward = function(encodedCredential, containerName, taskId, userId, handler) {
    var __args = arguments;
    if (__args.length === 5 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'string' && typeof __args[4] === 'function') {
      j_kieServerClientTaskService["taskForward(java.lang.String,java.lang.String,java.lang.Long,java.lang.String,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), userId, function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskRelease = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskRelease(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskResume = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskResume(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskSkip = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskSkip(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param handler {function} 
   */
  this.taskSuspend = function(encodedCredential, containerName, taskId, handler) {
    var __args = arguments;
    if (__args.length === 4 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'function') {
      j_kieServerClientTaskService["taskSuspend(java.lang.String,java.lang.String,java.lang.Long,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), function(ar) {
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
   @param encodedCredential {string} 
   @param containerName {string} 
   @param taskId {number} 
   @param nominations {Array.<string>} 
   @param handler {function} 
   */
  this.taskNominate = function(encodedCredential, containerName, taskId, nominations, handler) {
    var __args = arguments;
    if (__args.length === 5 && typeof __args[0] === 'string' && typeof __args[1] === 'string' && typeof __args[2] ==='number' && typeof __args[3] === 'object' && __args[3] instanceof Array && typeof __args[4] === 'function') {
      j_kieServerClientTaskService["taskNominate(java.lang.String,java.lang.String,java.lang.Long,java.util.List,io.vertx.core.Handler)"](encodedCredential, containerName, utils.convParamLong(taskId), utils.convParamListBasicOther(nominations), function(ar) {
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
  this._jdel = j_kieServerClientTaskService;
};

/**

 @memberof module:kieserver-js/kie_server_client_task_service
 @param vertx {Vertx} 
 @param address {string} 
 @return {KieServerClientTaskService}
 */
KieServerClientTaskService.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JKieServerClientTaskService["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), KieServerClientTaskService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = KieServerClientTaskService;