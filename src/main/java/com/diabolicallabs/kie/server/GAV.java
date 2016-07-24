package com.diabolicallabs.kie.server;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class GAV {

  public String version;
  public String groupId;
  public String artifactId;

  public GAV() {

  }

  public GAV(GAV other) {
    this();
    this.version = other.version;
    this.groupId = other.groupId;
    this.artifactId = other.artifactId;
  }

  public GAV(String groupId, String artifactId, String version) {
    this.version = version;
    this.groupId = groupId;
    this.artifactId = artifactId;
  }

  public GAV(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public GAV(JsonObject json) {

    if (json.containsKey("version")) version = json.getString("version");
    if (json.containsKey("group-id")) groupId = json.getString("group-id");
    if (json.containsKey("artifact-id")) artifactId = json.getString("artifact-id");

  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject()
      .put("version", version)
      .put("group-id", groupId)
      .put("artifact-id", artifactId);

    return json;
  }

}
