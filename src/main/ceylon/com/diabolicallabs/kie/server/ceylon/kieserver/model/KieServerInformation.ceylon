import com.diabolicallabs.kie.server.model {
  KieServerInformation_=KieServerInformation
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
/* Generated from com.diabolicallabs.kie.server.model.KieServerInformation */
shared class KieServerInformation() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object kieServerInformation {

  shared KieServerInformation fromJson(JsonObject json) {
    return KieServerInformation {
    };
  }

  shared object toCeylon extends Converter<KieServerInformation_, KieServerInformation>() {
    shared actual KieServerInformation convert(KieServerInformation_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<KieServerInformation, KieServerInformation_>() {
    shared actual KieServerInformation_ convert(KieServerInformation src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = KieServerInformation_(json);
      return ret;
    }
  }
  shared JsonObject toJson(KieServerInformation obj) => obj.toJson();
}
