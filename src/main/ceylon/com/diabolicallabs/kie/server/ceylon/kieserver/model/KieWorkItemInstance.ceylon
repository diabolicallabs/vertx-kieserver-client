import com.diabolicallabs.kie.server.model {
  KieWorkItemInstance_=KieWorkItemInstance
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
/* Generated from com.diabolicallabs.kie.server.model.KieWorkItemInstance */
shared class KieWorkItemInstance() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object kieWorkItemInstance {

  shared KieWorkItemInstance fromJson(JsonObject json) {
    return KieWorkItemInstance {
    };
  }

  shared object toCeylon extends Converter<KieWorkItemInstance_, KieWorkItemInstance>() {
    shared actual KieWorkItemInstance convert(KieWorkItemInstance_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<KieWorkItemInstance, KieWorkItemInstance_>() {
    shared actual KieWorkItemInstance_ convert(KieWorkItemInstance src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = KieWorkItemInstance_(json);
      return ret;
    }
  }
  shared JsonObject toJson(KieWorkItemInstance obj) => obj.toJson();
}
