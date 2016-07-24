package com.diabolicallabs.kie.server;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DataObject
public class KieUserTaskDefinition {
  public String name;
  public Integer priority;
  public String createdBy;
  public Boolean skippable;
  public List<String> associatedEntities = new ArrayList<>();
  public Map<String, String> taskInputs = new HashMap<>();
  public Map<String,String> taskOutputs = new HashMap<>();

  public KieUserTaskDefinition() {

  }

  public KieUserTaskDefinition(KieUserTaskDefinition other) {
    this();
    this.name = other.name;
    this.priority = other.priority;
    this.createdBy = other.createdBy;
    this.skippable = other.skippable;
    this.associatedEntities = other.associatedEntities;
    this.taskInputs = other.taskInputs;
    this.taskOutputs = other.taskOutputs;
  }

  public KieUserTaskDefinition(String jsonString) {
    this(new JsonObject(jsonString));
  }

  public KieUserTaskDefinition(JsonObject json) {

    if (json.containsKey("task-name")) name = json.getString("task-name");
    if (json.containsKey("task-priority")) priority = json.getInteger("task-priority");
    if (json.containsKey("task-created-by")) createdBy = json.getString("task-created-by");
    if (json.containsKey("task-skippable")) skippable = json.getBoolean("task-skippable");
    if (json.containsKey("associated-entities")) associatedEntities = json.getJsonArray("associated-entities").getList();
    if (json.containsKey("task-inputs")) taskInputs = json.getJsonObject("task-inputs").getMap().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
    if (json.containsKey("task-outputs")) taskOutputs = json.getJsonObject("task-outputs").getMap().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));

  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();

    if (name != null) json.put("task-name", name);
    if (priority != null) json.put("task-priority", priority);
    if (createdBy != null) json.put("task-created-by", createdBy);
    if (skippable != null) json.put("task-skippable", skippable);
    if (associatedEntities != null) json.put("associated-entities", associatedEntities);
    if (taskInputs != null) json.put("task-inputs", taskInputs);
    if (taskOutputs != null) json.put("task-outputs", taskOutputs);

    return json;
  }

}
