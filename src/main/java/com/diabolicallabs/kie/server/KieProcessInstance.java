package com.diabolicallabs.kie.server;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class KieProcessInstance {

  public String initiator;
  public Long processInstanceId;
  public String processId;
  public String processName;
  public String processVersion;
  public Integer processInstanceState;
  public String containerId;
  public Long startDate;
  public String processInstanceDescription;
  public String correlationKey;
  public Long parentInstanceId;

  public KieProcessInstance() {

  }

  public KieProcessInstance(KieProcessInstance other) {
    this();
    this.initiator = other.initiator;
    this.processInstanceId = other.processInstanceId;
    this.processId = other.processId;
    this.processName = other.processName;
    this.processVersion = other.processVersion;
    this.processInstanceState = other.processInstanceState;
    this.containerId = other.containerId;
    this.startDate = other.startDate;
    this.processInstanceDescription = other.processInstanceDescription;
    this.correlationKey = other.correlationKey;
    this.parentInstanceId = other.parentInstanceId;
  }

  public KieProcessInstance(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public KieProcessInstance(JsonObject json) {

    if (json.containsKey("initiator")) initiator = json.getString("initiator");
    if (json.containsKey("process-instance-id")) processInstanceId = json.getLong("process-instance-id");
    if (json.containsKey("process-id")) processId = json.getString("process-id");
    if (json.containsKey("process-name")) processName = json.getString("process-name");
    if (json.containsKey("process-version")) processVersion = json.getString("process-version");
    if (json.containsKey("process-instance-state")) processInstanceState = json.getInteger("process-instance-state");
    if (json.containsKey("container-id")) containerId = json.getString("container-id");
    if (json.containsKey("start-date")) startDate = json.getLong("start-date");
    if (json.containsKey("process-instance-desc")) processInstanceDescription = json.getString("process-instance-desc");
    if (json.containsKey("correlation-key")) correlationKey = json.getString("correlation-key");
    if (json.containsKey("parent-instance-id")) parentInstanceId = json.getLong("parent-instance-id");

  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();
    if (initiator != null) json.put("initiator", initiator);
    if (processInstanceId != null) json.put("process-instance-id", processInstanceId);
    if (processId != null) json.put("process-id", processId);
    if (processName != null) json.put("process-name", processName);
    if (processVersion != null) json.put("process-version", processVersion);
    if (processInstanceState != null) json.put("process-instance-state", processInstanceState);
    if (containerId != null) json.put("container-id", containerId);
    if (startDate != null) json.put("start-date", startDate);
    if (processInstanceDescription != null) json.put("process-instance-desc", processInstanceDescription);
    if (correlationKey != null) json.put("correlation-key", correlationKey);
    if (parentInstanceId != null) json.put("parent-instance-id", parentInstanceId);

    return json;
  }

}
