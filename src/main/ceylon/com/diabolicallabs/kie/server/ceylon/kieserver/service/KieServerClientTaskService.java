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
public class KieServerClientTaskService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.kie.server.service.KieServerClientTaskService, KieServerClientTaskService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.kie.server.service.KieServerClientTaskService, KieServerClientTaskService>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.kie.server.service.KieServerClientTaskService, KieServerClientTaskService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.kie.server.service.KieServerClientTaskService, KieServerClientTaskService>() {
        public KieServerClientTaskService convert(com.diabolicallabs.kie.server.service.KieServerClientTaskService src) {
          return new KieServerClientTaskService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<KieServerClientTaskService, com.diabolicallabs.kie.server.service.KieServerClientTaskService> TO_JAVA = new io.vertx.lang.ceylon.Converter<KieServerClientTaskService, com.diabolicallabs.kie.server.service.KieServerClientTaskService>() {
    public com.diabolicallabs.kie.server.service.KieServerClientTaskService convert(KieServerClientTaskService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(KieServerClientTaskService.class);
  @Ignore private final com.diabolicallabs.kie.server.service.KieServerClientTaskService delegate;

  public KieServerClientTaskService(com.diabolicallabs.kie.server.service.KieServerClientTaskService delegate) {
    this.delegate = delegate;
  }

  @Ignore 
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskActivate(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskActivate(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskClaim(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskClaim(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskStart(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskStart(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskStop(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskStop(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskComplete(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.json::Object") @Name("variables")  ceylon.json.Object variables, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.json.JsonObject arg_3 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(variables);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskComplete(arg_0, arg_1, arg_2, arg_3, arg_4);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskDelegate(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("delegateId")  ceylon.language.String delegateId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_3 = io.vertx.lang.ceylon.ToJava.String.safeConvert(delegateId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskDelegate(arg_0, arg_1, arg_2, arg_3, arg_4);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskExit(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskExit(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskFail(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskFail(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskForward(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::String") @Name("userId")  ceylon.language.String userId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.lang.String arg_3 = io.vertx.lang.ceylon.ToJava.String.safeConvert(userId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskForward(arg_0, arg_1, arg_2, arg_3, arg_4);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskRelease(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskRelease(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskResume(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskResume(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskSkip(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskSkip(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskSuspend(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskSuspend(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void taskNominate(
    final @TypeInfo("ceylon.language::String") @Name("encodedCredential")  ceylon.language.String encodedCredential, 
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("taskId")  ceylon.language.Integer taskId, 
    final @TypeInfo("ceylon.language::List<ceylon.language::String>") @Name("nominations")  ceylon.language.List<ceylon.language.String> nominations, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(encodedCredential);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(taskId);
    java.util.List<java.lang.String> arg_3 = io.vertx.lang.ceylon.ToJava.convertList(nominations, io.vertx.lang.ceylon.ToJava.String);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.taskNominate(arg_0, arg_1, arg_2, arg_3, arg_4);
  }

}
