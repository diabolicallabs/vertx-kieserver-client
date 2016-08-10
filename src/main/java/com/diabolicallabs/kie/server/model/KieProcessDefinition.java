package com.diabolicallabs.kie.server.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class KieProcessDefinition {

  public String processId;
  public String processName;
  public String processVersion;
  public String packageName;
  public String containerId;

  public KieProcessDefinition() {

  }

  public KieProcessDefinition(KieProcessDefinition other) {
    this();
    this.processId = other.processId;
    this.processName = other.processName;
    this.processVersion = other.processVersion;
    this.packageName = other.packageName;
    this.containerId = other.containerId;
  }

  public KieProcessDefinition(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public KieProcessDefinition(JsonObject json) {

    if (json.containsKey("process-id")) processId = json.getString("process-id");
    if (json.containsKey("process-name")) processName = json.getString("process-name");
    if (json.containsKey("process-version")) processVersion = json.getString("process-version");
    if (json.containsKey("package")) packageName = json.getString("package");
    if (json.containsKey("container-id")) containerId = json.getString("container-id");

  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();
    if (processId != null) json.put("process-id", processId);
    if (processName != null) json.put("process-name", processName);
    if (processVersion != null) json.put("process-version", processVersion);
    if (packageName != null) json.put("package", packageName);
    if (containerId != null) json.put("container-id", containerId);

    return json;
  }

}
