import com.diabolicallabs.kie.server.model {
  KieUserTaskDefinition_=KieUserTaskDefinition
}
import ceylon.json {
  JsonObject=Object,
  JsonArray=Array,
  parse
}
import io.vertx.lang.ceylon {
  BaseDataObject,
  Converter,
  ToJava
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from com.diabolicallabs.kie.server.model.KieUserTaskDefinition */
shared class KieUserTaskDefinition() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object kieUserTaskDefinition {

  shared KieUserTaskDefinition fromJson(JsonObject json) {
    return KieUserTaskDefinition {
    };
  }

  shared object toCeylon extends Converter<KieUserTaskDefinition_, KieUserTaskDefinition>() {
    shared actual KieUserTaskDefinition convert(KieUserTaskDefinition_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<KieUserTaskDefinition, KieUserTaskDefinition_>() {
    shared actual KieUserTaskDefinition_ convert(KieUserTaskDefinition src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = KieUserTaskDefinition_(json);
      return ret;
    }
  }
  shared JsonObject toJson(KieUserTaskDefinition obj) => obj.toJson();
}
