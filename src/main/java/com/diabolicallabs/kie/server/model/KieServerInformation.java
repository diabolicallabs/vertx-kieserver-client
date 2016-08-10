package com.diabolicallabs.kie.server.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject
public class KieServerInformation {

  public String id;
  public String version;
  public String name;
  public String location;
  public List<String> capabilities = new ArrayList<>();

  public KieServerInformation() {

  }

  public KieServerInformation(KieServerInformation other) {
    this();
    this.id = other.id;
    this.version = other.version;
    this.name = other.name;
    this.location = other.location;
    this.capabilities = other.capabilities;
  }

  public KieServerInformation(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public KieServerInformation(JsonObject json) {

    if (json.containsKey("id")) id = json.getString("id");
    if (json.containsKey("version")) version = json.getString("version");
    if (json.containsKey("name")) name = json.getString("name");
    if (json.containsKey("location")) location = json.getString("location");
    if (json.containsKey("capabilities")) capabilities = json.getJsonArray("capabilities").getList();
  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();
    if (id != null) json.put("id", id);
    if (version != null) json.put("version", version);
    if (name != null) json.put("name", name);
    if (location != null) json.put("location", location);
    if (capabilities.size() != 0) json.put("capabilities", new JsonArray(capabilities));

    return json;
  }

}
