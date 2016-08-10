package com.diabolicallabs.kie.server.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject
public class KieTaskEntityDefinition {

  public String name;
  public List<String> entities = new ArrayList<>();

  public KieTaskEntityDefinition() {
  }

  public KieTaskEntityDefinition(KieTaskEntityDefinition other) {
    this();
    this.name = other.name;
    this.entities = other.entities;
  }

  public KieTaskEntityDefinition(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public KieTaskEntityDefinition(JsonObject json) {

    name = json.fieldNames().iterator().next();
    entities = json.getJsonArray(name).getList();
  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();
    json.put(name, entities);

    return json;
  }

}
