import com.diabolicallabs.kie.server.model {
  KieProcessDefinition_=KieProcessDefinition
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
/* Generated from com.diabolicallabs.kie.server.model.KieProcessDefinition */
shared class KieProcessDefinition() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object kieProcessDefinition {

  shared KieProcessDefinition fromJson(JsonObject json) {
    return KieProcessDefinition {
    };
  }

  shared object toCeylon extends Converter<KieProcessDefinition_, KieProcessDefinition>() {
    shared actual KieProcessDefinition convert(KieProcessDefinition_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<KieProcessDefinition, KieProcessDefinition_>() {
    shared actual KieProcessDefinition_ convert(KieProcessDefinition src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = KieProcessDefinition_(json);
      return ret;
    }
  }
  shared JsonObject toJson(KieProcessDefinition obj) => obj.toJson();
}
