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
import java.util.List
import io.vertx.groovy.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
@CompileStatic
public class KieServerClientTaskService {
  private final def com.diabolicallabs.kie.server.service.KieServerClientTaskService delegate;
  public KieServerClientTaskService(Object delegate) {
    this.delegate = (com.diabolicallabs.kie.server.service.KieServerClientTaskService) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public static KieServerClientTaskService createProxy(Vertx vertx, String address) {
    def ret = InternalHelper.safeCreate(com.diabolicallabs.kie.server.service.KieServerClientTaskService.createProxy(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, address), com.diabolicallabs.kie.server.groovy.service.KieServerClientTaskService.class);
    return ret;
  }
  public void taskActivate(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskActivate(encodedCredential, containerName, taskId, handler);
  }
  public void taskClaim(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskClaim(encodedCredential, containerName, taskId, handler);
  }
  public void taskStart(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskStart(encodedCredential, containerName, taskId, handler);
  }
  public void taskStop(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskStop(encodedCredential, containerName, taskId, handler);
  }
  public void taskComplete(String encodedCredential, String containerName, Long taskId, Map<String, Object> variables, Handler<AsyncResult<Void>> handler) {
    delegate.taskComplete(encodedCredential, containerName, taskId, variables != null ? new io.vertx.core.json.JsonObject(variables) : null, handler);
  }
  public void taskDelegate(String encodedCredential, String containerName, Long taskId, String delegateId, Handler<AsyncResult<Void>> handler) {
    delegate.taskDelegate(encodedCredential, containerName, taskId, delegateId, handler);
  }
  public void taskExit(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskExit(encodedCredential, containerName, taskId, handler);
  }
  public void taskFail(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskFail(encodedCredential, containerName, taskId, handler);
  }
  public void taskForward(String encodedCredential, String containerName, Long taskId, String userId, Handler<AsyncResult<Void>> handler) {
    delegate.taskForward(encodedCredential, containerName, taskId, userId, handler);
  }
  public void taskRelease(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskRelease(encodedCredential, containerName, taskId, handler);
  }
  public void taskResume(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskResume(encodedCredential, containerName, taskId, handler);
  }
  public void taskSkip(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskSkip(encodedCredential, containerName, taskId, handler);
  }
  public void taskSuspend(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {
    delegate.taskSuspend(encodedCredential, containerName, taskId, handler);
  }
  public void taskNominate(String encodedCredential, String containerName, Long taskId, List<String> nominations, Handler<AsyncResult<Void>> handler) {
    delegate.taskNominate(encodedCredential, containerName, taskId, nominations != null ? (List)nominations.collect({it}) : null, handler);
  }
}
