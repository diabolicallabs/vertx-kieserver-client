package com.diabolicallabs.kie.server.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class KieWorkItemInstance {

  public Long id;
  public String name;
  public Integer state;
  public JsonObject parameters = new JsonObject();
  public Long processInstanceId;
  public String containerId;
  public Long nodeInstanceId;
  public Long nodeId;

  public KieWorkItemInstance() {

  }

  public KieWorkItemInstance(KieWorkItemInstance other) {
    this();
    this.id = other.id;
    this.name = other.name;
    this.state = other.state;
    this.parameters = other.parameters;
    this.processInstanceId = other.processInstanceId;
    this.containerId = other.containerId;
    this.nodeInstanceId = other.nodeInstanceId;
    this.nodeId = other.nodeId;
  }

  public KieWorkItemInstance(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public KieWorkItemInstance(JsonObject json) {

    if (json.containsKey("work-item-id")) id = json.getLong("work-item-id");
    if (json.containsKey("work-item-name")) name = json.getString("work-item-name");
    if (json.containsKey("work-item-state")) state = json.getInteger("work-item-state");
    if (json.containsKey("work-item-params")) parameters = json.getJsonObject("work-item-params");
    if (json.containsKey("process-instance-id")) processInstanceId = json.getLong("process-instance-id");
    if (json.containsKey("container-id")) containerId = json.getString("container-id");
    if (json.containsKey("node-instance-id")) nodeInstanceId = json.getLong("node-instance-id");
    if (json.containsKey("node-id")) nodeId = json.getLong("node-id");

  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();

    if (id != null) json.put("work-item-id", id);
    if (name != null) json.put("work-item-name", name);
    if (state != null) json.put("work-item-state", state);
    if (parameters != null) json.put("work-item-params", parameters);
    if (processInstanceId != null) json.put("process-instance-id", processInstanceId);
    if (containerId != null) json.put("container-id", containerId);
    if (nodeInstanceId != null) json.put("node-instance-id", nodeInstanceId);
    if (nodeId != null) json.put("node-id", nodeId);

    return json;
  }

}
