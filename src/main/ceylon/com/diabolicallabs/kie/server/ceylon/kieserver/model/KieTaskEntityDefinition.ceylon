import com.diabolicallabs.kie.server.model {
  KieTaskEntityDefinition_=KieTaskEntityDefinition
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
/* Generated from com.diabolicallabs.kie.server.model.KieTaskEntityDefinition */
shared class KieTaskEntityDefinition() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object kieTaskEntityDefinition {

  shared KieTaskEntityDefinition fromJson(JsonObject json) {
    return KieTaskEntityDefinition {
    };
  }

  shared object toCeylon extends Converter<KieTaskEntityDefinition_, KieTaskEntityDefinition>() {
    shared actual KieTaskEntityDefinition convert(KieTaskEntityDefinition_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<KieTaskEntityDefinition, KieTaskEntityDefinition_>() {
    shared actual KieTaskEntityDefinition_ convert(KieTaskEntityDefinition src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = KieTaskEntityDefinition_(json);
      return ret;
    }
  }
  shared JsonObject toJson(KieTaskEntityDefinition obj) => obj.toJson();
}
