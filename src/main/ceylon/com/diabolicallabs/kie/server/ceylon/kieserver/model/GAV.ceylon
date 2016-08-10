import com.diabolicallabs.kie.server.model {
  GAV_=GAV
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
/* Generated from com.diabolicallabs.kie.server.model.GAV */
shared class GAV() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object gav {

  shared GAV fromJson(JsonObject json) {
    return GAV {
    };
  }

  shared object toCeylon extends Converter<GAV_, GAV>() {
    shared actual GAV convert(GAV_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<GAV, GAV_>() {
    shared actual GAV_ convert(GAV src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = GAV_(json);
      return ret;
    }
  }
  shared JsonObject toJson(GAV obj) => obj.toJson();
}
