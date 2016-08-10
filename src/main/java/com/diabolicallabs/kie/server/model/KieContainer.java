package com.diabolicallabs.kie.server.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class KieContainer {

  public String id;
  public String status;
  public GAV releaseGAV;
  public GAV resolvedGAV;

  public KieContainer() {

  }

  public KieContainer(KieContainer other) {
    this();
    this.id = other.id;
    this.status = other.status;
    this.releaseGAV = other.releaseGAV;
    this.resolvedGAV = other.resolvedGAV;
  }

  public KieContainer(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public KieContainer(JsonObject json) {

    if (json.containsKey("container-id")) id = json.getString("container-id");
    if (json.containsKey("status")) status = json.getString("status");
    if (json.containsKey("release-id")) releaseGAV = new GAV(json.getJsonObject("release-id"));
    if (json.containsKey("resolved-release-id")) resolvedGAV = new GAV(json.getJsonObject("resolved-release-id"));

  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();
    if (id != null) json.put("container-id", id);
    if (status != null) json.put("status", status);
    if (releaseGAV != null) json.put("release-id", releaseGAV.toJson());
    if (resolvedGAV != null) json.put("resolved-release-id", resolvedGAV.toJson());

    return json;
  }

}
