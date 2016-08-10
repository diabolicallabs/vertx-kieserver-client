import com.diabolicallabs.kie.server.model {
  KieProcessInstance_=KieProcessInstance
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
/* Generated from com.diabolicallabs.kie.server.model.KieProcessInstance */
shared class KieProcessInstance() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object kieProcessInstance {

  shared KieProcessInstance fromJson(JsonObject json) {
    return KieProcessInstance {
    };
  }

  shared object toCeylon extends Converter<KieProcessInstance_, KieProcessInstance>() {
    shared actual KieProcessInstance convert(KieProcessInstance_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<KieProcessInstance, KieProcessInstance_>() {
    shared actual KieProcessInstance_ convert(KieProcessInstance src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = KieProcessInstance_(json);
      return ret;
    }
  }
  shared JsonObject toJson(KieProcessInstance obj) => obj.toJson();
}
