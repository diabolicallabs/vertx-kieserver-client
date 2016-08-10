import com.diabolicallabs.kie.server.model {
  KieContainer_=KieContainer
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
/* Generated from com.diabolicallabs.kie.server.model.KieContainer */
shared class KieContainer() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object kieContainer {

  shared KieContainer fromJson(JsonObject json) {
    return KieContainer {
    };
  }

  shared object toCeylon extends Converter<KieContainer_, KieContainer>() {
    shared actual KieContainer convert(KieContainer_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<KieContainer, KieContainer_>() {
    shared actual KieContainer_ convert(KieContainer src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = KieContainer_(json);
      return ret;
    }
  }
  shared JsonObject toJson(KieContainer obj) => obj.toJson();
}
