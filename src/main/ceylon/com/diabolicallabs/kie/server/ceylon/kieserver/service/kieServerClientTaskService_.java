package com.diabolicallabs.kie.server.ceylon.kieserver.service;

import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import java.util.List;
import io.vertx.ceylon.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
@Name("kieServerClientTaskService")
@com.redhat.ceylon.compiler.java.metadata.Object
public class kieServerClientTaskService_ implements ReifiedType {

  private static final kieServerClientTaskService_ instance = new kieServerClientTaskService_();
  public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(kieServerClientTaskService_.class);

  @Ignore
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  @TypeInfo("com.diabolicallabs.kie.server.ceylon.kieserver.service::kieServerClientTaskService")
  public static kieServerClientTaskService_ get_() {
    return instance;
  }


  @TypeInfo("com.diabolicallabs.kie.server.ceylon.kieserver.service::KieServerClientTaskService")
  public KieServerClientTaskService createProxy(
    final @TypeInfo("io.vertx.ceylon.core::Vertx") @Name("vertx")  Vertx vertx, 
    final @TypeInfo("ceylon.language::String") @Name("address")  ceylon.language.String address) {
    io.vertx.core.Vertx arg_0 = io.vertx.ceylon.core.Vertx.TO_JAVA.safeConvert(vertx);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(address);
    KieServerClientTaskService ret = com.diabolicallabs.kie.server.ceylon.kieserver.service.KieServerClientTaskService.TO_CEYLON.converter().safeConvert(com.diabolicallabs.kie.server.service.KieServerClientTaskService.createProxy(arg_0, arg_1));
    return ret;
  }

}
