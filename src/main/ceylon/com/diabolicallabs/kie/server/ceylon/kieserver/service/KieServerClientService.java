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
import io.vertx.ceylon.core.Vertx;
import java.util.List;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@Ceylon(major = 8)
public class KieServerClientService implements ReifiedType {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.kie.server.service.KieServerClientService, KieServerClientService> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<com.diabolicallabs.kie.server.service.KieServerClientService, KieServerClientService>() {
    public io.vertx.lang.ceylon.Converter<com.diabolicallabs.kie.server.service.KieServerClientService, KieServerClientService> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<com.diabolicallabs.kie.server.service.KieServerClientService, KieServerClientService>() {
        public KieServerClientService convert(com.diabolicallabs.kie.server.service.KieServerClientService src) {
          return new KieServerClientService(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<KieServerClientService, com.diabolicallabs.kie.server.service.KieServerClientService> TO_JAVA = new io.vertx.lang.ceylon.Converter<KieServerClientService, com.diabolicallabs.kie.server.service.KieServerClientService>() {
    public com.diabolicallabs.kie.server.service.KieServerClientService convert(KieServerClientService src) {
      return src.delegate;
    }
  };

  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(KieServerClientService.class);
  @Ignore private final com.diabolicallabs.kie.server.service.KieServerClientService delegate;

  public KieServerClientService(com.diabolicallabs.kie.server.service.KieServerClientService delegate) {
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
  public void information(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.kie.server.ceylon.kieserver.model::KieServerInformation)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.kie.server.model.KieServerInformation>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.kie.server.model.KieServerInformation>(handler) {
      public Object toCeylon(com.diabolicallabs.kie.server.model.KieServerInformation event) {
        return com.diabolicallabs.kie.server.ceylon.kieserver.model.kieServerInformation_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.information(arg_0);
  }

  @TypeInfo("ceylon.language::Anything")
  public void createContainer(
    final @TypeInfo("ceylon.language::String") @Name("name")  ceylon.language.String name, 
    final @TypeInfo("com.diabolicallabs.kie.server.ceylon.kieserver.model::GAV") @Name("gav")  com.diabolicallabs.kie.server.ceylon.kieserver.model.GAV gav, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.kie.server.ceylon.kieserver.model::KieContainer)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(name);
    com.diabolicallabs.kie.server.model.GAV arg_1 = gav == null ? null : new com.diabolicallabs.kie.server.model.GAV(io.vertx.lang.ceylon.ToJava.JsonObject.convert(gav.toJson()));
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.kie.server.model.KieContainer>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.kie.server.model.KieContainer>(handler) {
      public Object toCeylon(com.diabolicallabs.kie.server.model.KieContainer event) {
        return com.diabolicallabs.kie.server.ceylon.kieserver.model.kieContainer_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.createContainer(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void container(
    final @TypeInfo("ceylon.language::String") @Name("name")  ceylon.language.String name, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.kie.server.ceylon.kieserver.model::KieContainer)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(name);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.kie.server.model.KieContainer>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.kie.server.model.KieContainer>(handler) {
      public Object toCeylon(com.diabolicallabs.kie.server.model.KieContainer event) {
        return com.diabolicallabs.kie.server.ceylon.kieserver.model.kieContainer_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.container(arg_0, arg_1);
  }

  @TypeInfo("ceylon.language::Anything")
  public void containers(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<com.diabolicallabs.kie.server.ceylon.kieserver.model::KieContainer>)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieContainer>>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<com.diabolicallabs.kie.server.model.KieContainer>>(handler) {
      public Object toCeylon(java.util.List<com.diabolicallabs.kie.server.model.KieContainer> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(com.diabolicallabs.kie.server.ceylon.kieserver.model.KieContainer.$TypeDescriptor$, event, com.diabolicallabs.kie.server.ceylon.kieserver.model.kieContainer_.get_().getToCeylon());
      }
    };
    delegate.containers(arg_0);
  }

  @TypeInfo("ceylon.language::Anything")
  public void deleteContainer(
    final @TypeInfo("ceylon.language::String") @Name("name")  ceylon.language.String name, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(name);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.deleteContainer(arg_0, arg_1);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processDefinitions(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<com.diabolicallabs.kie.server.ceylon.kieserver.model::KieProcessDefinition>)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieProcessDefinition>>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<com.diabolicallabs.kie.server.model.KieProcessDefinition>>(handler) {
      public Object toCeylon(java.util.List<com.diabolicallabs.kie.server.model.KieProcessDefinition> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(com.diabolicallabs.kie.server.ceylon.kieserver.model.KieProcessDefinition.$TypeDescriptor$, event, com.diabolicallabs.kie.server.ceylon.kieserver.model.kieProcessDefinition_.get_().getToCeylon());
      }
    };
    delegate.processDefinitions(arg_0);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processVariableDefinitions(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    delegate.processVariableDefinitions(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processServiceTaskDefinitions(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    delegate.processServiceTaskDefinitions(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processUserTaskDefinitions(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<com.diabolicallabs.kie.server.ceylon.kieserver.model::KieUserTaskDefinition>)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieUserTaskDefinition>>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<com.diabolicallabs.kie.server.model.KieUserTaskDefinition>>(handler) {
      public Object toCeylon(java.util.List<com.diabolicallabs.kie.server.model.KieUserTaskDefinition> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(com.diabolicallabs.kie.server.ceylon.kieserver.model.KieUserTaskDefinition.$TypeDescriptor$, event, com.diabolicallabs.kie.server.ceylon.kieserver.model.kieUserTaskDefinition_.get_().getToCeylon());
      }
    };
    delegate.processUserTaskDefinitions(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processSubprocessDefinitions(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<ceylon.language::String>)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<java.lang.String>>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<java.lang.String>>(handler) {
      public Object toCeylon(java.util.List<java.lang.String> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(ceylon.language.String.$TypeDescriptor$, event, io.vertx.lang.ceylon.ToCeylon.String);
      }
    };
    delegate.processSubprocessDefinitions(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processEntityDefinitions(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    delegate.processEntityDefinitions(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processUserTaskInputDefinitions(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::String") @Name("taskName")  ceylon.language.String taskName, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(taskName);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    delegate.processUserTaskInputDefinitions(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processUserTaskOutputDefinitions(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::String") @Name("taskName")  ceylon.language.String taskName, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.json::Object)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(taskName);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.json.JsonObject>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.json.JsonObject>(handler) {
      public Object toCeylon(io.vertx.core.json.JsonObject event) {
        return io.vertx.lang.ceylon.ToCeylon.JsonObject.safeConvert(event);
      }
    };
    delegate.processUserTaskOutputDefinitions(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstances(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<com.diabolicallabs.kie.server.ceylon.kieserver.model::KieProcessInstance>)") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieProcessInstance>>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<com.diabolicallabs.kie.server.model.KieProcessInstance>>(handler) {
      public Object toCeylon(java.util.List<com.diabolicallabs.kie.server.model.KieProcessInstance> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(com.diabolicallabs.kie.server.ceylon.kieserver.model.KieProcessInstance.$TypeDescriptor$, event, com.diabolicallabs.kie.server.ceylon.kieserver.model.kieProcessInstance_.get_().getToCeylon());
      }
    };
    delegate.processInstances(arg_0);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstance(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.kie.server.ceylon.kieserver.model::KieProcessInstance)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.kie.server.model.KieProcessInstance>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.kie.server.model.KieProcessInstance>(handler) {
      public Object toCeylon(com.diabolicallabs.kie.server.model.KieProcessInstance event) {
        return com.diabolicallabs.kie.server.ceylon.kieserver.model.kieProcessInstance_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.processInstance(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void startProcess(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.json::Object") @Name("vars")  ceylon.json.Object vars, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(vars);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Long>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Long>(handler) {
      public Object toCeylon(java.lang.Long event) {
        return io.vertx.lang.ceylon.ToCeylon.Long.safeConvert(event);
      }
    };
    delegate.startProcess(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void startProcessWithCorrelation(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("processId")  ceylon.language.String processId, 
    final @TypeInfo("ceylon.language::String") @Name("correlationId")  ceylon.language.String correlationId, 
    final @TypeInfo("ceylon.json::Object") @Name("vars")  ceylon.json.Object vars, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::Integer)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(processId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(correlationId);
    io.vertx.core.json.JsonObject arg_3 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(vars);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Long>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Long>(handler) {
      public Object toCeylon(java.lang.Long event) {
        return io.vertx.lang.ceylon.ToCeylon.Long.safeConvert(event);
      }
    };
    delegate.startProcessWithCorrelation(arg_0, arg_1, arg_2, arg_3, arg_4);
  }

  @TypeInfo("ceylon.language::Anything")
  public void deleteProcessInstance(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.deleteProcessInstance(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceSignals(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<ceylon.language::String>)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<java.lang.String>>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<java.lang.String>>(handler) {
      public Object toCeylon(java.util.List<java.lang.String> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(ceylon.language.String.$TypeDescriptor$, event, io.vertx.lang.ceylon.ToCeylon.String);
      }
    };
    delegate.processInstanceSignals(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceVariableValue(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::String") @Name("variableName")  ceylon.language.String variableName, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::String)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(variableName);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.String>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.String>(handler) {
      public Object toCeylon(java.lang.String event) {
        return io.vertx.lang.ceylon.ToCeylon.String.safeConvert(event);
      }
    };
    delegate.processInstanceVariableValue(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceUpdateVariableValue(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::String") @Name("variableName")  ceylon.language.String variableName, 
    final @TypeInfo("ceylon.language::String") @Name("value")  ceylon.language.String value, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(variableName);
    java.lang.String arg_3 = io.vertx.lang.ceylon.ToJava.String.safeConvert(value);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.processInstanceUpdateVariableValue(arg_0, arg_1, arg_2, arg_3, arg_4);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceUpdateVariables(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.json::Object") @Name("variables")  ceylon.json.Object variables, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(variables);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.processInstanceUpdateVariables(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceWorkItems(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|ceylon.language::List<com.diabolicallabs.kie.server.ceylon.kieserver.model::KieWorkItemInstance>)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.util.List<com.diabolicallabs.kie.server.model.KieWorkItemInstance>>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.util.List<com.diabolicallabs.kie.server.model.KieWorkItemInstance>>(handler) {
      public Object toCeylon(java.util.List<com.diabolicallabs.kie.server.model.KieWorkItemInstance> event) {
        return io.vertx.lang.ceylon.ToCeylon.convertList(com.diabolicallabs.kie.server.ceylon.kieserver.model.KieWorkItemInstance.$TypeDescriptor$, event, com.diabolicallabs.kie.server.ceylon.kieserver.model.kieWorkItemInstance_.get_().getToCeylon());
      }
    };
    delegate.processInstanceWorkItems(arg_0, arg_1, arg_2);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceWorkItem(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Integer") @Name("workItemId")  ceylon.language.Integer workItemId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|com.diabolicallabs.kie.server.ceylon.kieserver.model::KieWorkItemInstance)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(workItemId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<com.diabolicallabs.kie.server.model.KieWorkItemInstance>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<com.diabolicallabs.kie.server.model.KieWorkItemInstance>(handler) {
      public Object toCeylon(com.diabolicallabs.kie.server.model.KieWorkItemInstance event) {
        return com.diabolicallabs.kie.server.ceylon.kieserver.model.kieWorkItemInstance_.get_().getToCeylon().safeConvert(event);
      }
    };
    delegate.processInstanceWorkItem(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceAbortWorkItem(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Integer") @Name("workItemId")  ceylon.language.Integer workItemId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(workItemId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.processInstanceAbortWorkItem(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceCompleteWorkItem(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::Integer") @Name("workItemId")  ceylon.language.Integer workItemId, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    java.lang.Long arg_2 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(workItemId);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.processInstanceCompleteWorkItem(arg_0, arg_1, arg_2, arg_3);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstanceSignal(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::Integer") @Name("processInstanceId")  ceylon.language.Integer processInstanceId, 
    final @TypeInfo("ceylon.language::String") @Name("signalName")  ceylon.language.String signalName, 
    final @TypeInfo("ceylon.json::Object") @Name("signalPayload")  ceylon.json.Object signalPayload, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.Long arg_1 = io.vertx.lang.ceylon.ToJava.Long.safeConvert(processInstanceId);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(signalName);
    io.vertx.core.json.JsonObject arg_3 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(signalPayload);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.processInstanceSignal(arg_0, arg_1, arg_2, arg_3, arg_4);
  }

  @TypeInfo("ceylon.language::Anything")
  public void processInstancesSignal(
    final @TypeInfo("ceylon.language::String") @Name("containerName")  ceylon.language.String containerName, 
    final @TypeInfo("ceylon.language::String") @Name("signalName")  ceylon.language.String signalName, 
    final @TypeInfo("ceylon.json::Object") @Name("signalPayload")  ceylon.json.Object signalPayload, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler")  Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(containerName);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(signalName);
    io.vertx.core.json.JsonObject arg_2 = io.vertx.lang.ceylon.ToJava.JsonObject.safeConvert(signalPayload);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.processInstancesSignal(arg_0, arg_1, arg_2, arg_3);
  }

}
