require 'vertx/vertx'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.kie.server.service.KieServerClientService
module Kieserver
  class KieServerClientService
    # @private
    # @param j_del [::Kieserver::KieServerClientService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieserver::KieServerClientService] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieserver::KieServerClientService]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsKieServerService::KieServerClientService.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieserver::KieServerClientService)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @yield 
    # @return [void]
    def information
      if block_given?
        return @j_del.java_method(:information, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling information()"
    end
    # @param [String] name 
    # @param [Hash] gav 
    # @yield 
    # @return [void]
    def create_container(name=nil,gav=nil)
      if name.class == String && gav.class == Hash && block_given?
        return @j_del.java_method(:createContainer, [Java::java.lang.String.java_class,Java::ComDiabolicallabsKieServerModel::GAV.java_class,Java::IoVertxCore::Handler.java_class]).call(name,Java::ComDiabolicallabsKieServerModel::GAV.new(::Vertx::Util::Utils.to_json_object(gav)),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling create_container(name,gav)"
    end
    # @param [String] name 
    # @yield 
    # @return [void]
    def container(name=nil)
      if name.class == String && block_given?
        return @j_del.java_method(:container, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(name,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling container(name)"
    end
    # @yield 
    # @return [void]
    def containers
      if block_given?
        return @j_del.java_method(:containers, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt != nil ? JSON.parse(elt.toJson.encode) : nil } : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling containers()"
    end
    # @param [String] name 
    # @yield 
    # @return [void]
    def delete_container(name=nil)
      if name.class == String && block_given?
        return @j_del.java_method(:deleteContainer, [Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(name,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling delete_container(name)"
    end
    # @yield 
    # @return [void]
    def process_definitions
      if block_given?
        return @j_del.java_method(:processDefinitions, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt != nil ? JSON.parse(elt.toJson.encode) : nil } : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_definitions()"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @yield 
    # @return [void]
    def process_variable_definitions(containerName=nil,processId=nil)
      if containerName.class == String && processId.class == String && block_given?
        return @j_del.java_method(:processVariableDefinitions, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_variable_definitions(containerName,processId)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @yield 
    # @return [void]
    def process_service_task_definitions(containerName=nil,processId=nil)
      if containerName.class == String && processId.class == String && block_given?
        return @j_del.java_method(:processServiceTaskDefinitions, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_service_task_definitions(containerName,processId)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @yield 
    # @return [void]
    def process_user_task_definitions(containerName=nil,processId=nil)
      if containerName.class == String && processId.class == String && block_given?
        return @j_del.java_method(:processUserTaskDefinitions, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt != nil ? JSON.parse(elt.toJson.encode) : nil } : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_user_task_definitions(containerName,processId)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @yield 
    # @return [void]
    def process_subprocess_definitions(containerName=nil,processId=nil)
      if containerName.class == String && processId.class == String && block_given?
        return @j_del.java_method(:processSubprocessDefinitions, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt } : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_subprocess_definitions(containerName,processId)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @yield 
    # @return [void]
    def process_entity_definitions(containerName=nil,processId=nil)
      if containerName.class == String && processId.class == String && block_given?
        return @j_del.java_method(:processEntityDefinitions, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_entity_definitions(containerName,processId)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @param [String] taskName 
    # @yield 
    # @return [void]
    def process_user_task_input_definitions(containerName=nil,processId=nil,taskName=nil)
      if containerName.class == String && processId.class == String && taskName.class == String && block_given?
        return @j_del.java_method(:processUserTaskInputDefinitions, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,taskName,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_user_task_input_definitions(containerName,processId,taskName)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @param [String] taskName 
    # @yield 
    # @return [void]
    def process_user_task_output_definitions(containerName=nil,processId=nil,taskName=nil)
      if containerName.class == String && processId.class == String && taskName.class == String && block_given?
        return @j_del.java_method(:processUserTaskOutputDefinitions, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,taskName,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_user_task_output_definitions(containerName,processId,taskName)"
    end
    # @yield 
    # @return [void]
    def process_instances
      if block_given?
        return @j_del.java_method(:processInstances, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt != nil ? JSON.parse(elt.toJson.encode) : nil } : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instances()"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @yield 
    # @return [void]
    def process_instance(containerName=nil,processInstanceId=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && block_given?
        return @j_del.java_method(:processInstance, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance(containerName,processInstanceId)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @param [Hash{String => Object}] vars 
    # @yield 
    # @return [void]
    def start_process(containerName=nil,processId=nil,vars=nil)
      if containerName.class == String && processId.class == String && vars.class == Hash && block_given?
        return @j_del.java_method(:startProcess, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,::Vertx::Util::Utils.to_json_object(vars),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling start_process(containerName,processId,vars)"
    end
    # @param [String] containerName 
    # @param [String] processId 
    # @param [String] correlationId 
    # @param [Hash{String => Object}] vars 
    # @yield 
    # @return [void]
    def start_process_with_correlation(containerName=nil,processId=nil,correlationId=nil,vars=nil)
      if containerName.class == String && processId.class == String && correlationId.class == String && vars.class == Hash && block_given?
        return @j_del.java_method(:startProcessWithCorrelation, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processId,correlationId,::Vertx::Util::Utils.to_json_object(vars),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling start_process_with_correlation(containerName,processId,correlationId,vars)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @yield 
    # @return [void]
    def delete_process_instance(containerName=nil,processInstanceId=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && block_given?
        return @j_del.java_method(:deleteProcessInstance, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling delete_process_instance(containerName,processInstanceId)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @yield 
    # @return [void]
    def process_instance_signals(containerName=nil,processInstanceId=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && block_given?
        return @j_del.java_method(:processInstanceSignals, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt } : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_signals(containerName,processInstanceId)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @param [String] variableName 
    # @yield 
    # @return [void]
    def process_instance_variable_value(containerName=nil,processInstanceId=nil,variableName=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && variableName.class == String && block_given?
        return @j_del.java_method(:processInstanceVariableValue, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,variableName,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_variable_value(containerName,processInstanceId,variableName)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @param [String] variableName 
    # @param [String] value 
    # @yield 
    # @return [void]
    def process_instance_update_variable_value(containerName=nil,processInstanceId=nil,variableName=nil,value=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && variableName.class == String && value.class == String && block_given?
        return @j_del.java_method(:processInstanceUpdateVariableValue, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,variableName,value,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_update_variable_value(containerName,processInstanceId,variableName,value)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @param [Hash{String => Object}] variables 
    # @yield 
    # @return [void]
    def process_instance_update_variables(containerName=nil,processInstanceId=nil,variables=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && variables.class == Hash && block_given?
        return @j_del.java_method(:processInstanceUpdateVariables, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,::Vertx::Util::Utils.to_json_object(variables),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_update_variables(containerName,processInstanceId,variables)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @yield 
    # @return [void]
    def process_instance_work_items(containerName=nil,processInstanceId=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && block_given?
        return @j_del.java_method(:processInstanceWorkItems, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result.to_a.map { |elt| elt != nil ? JSON.parse(elt.toJson.encode) : nil } : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_work_items(containerName,processInstanceId)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @param [Fixnum] workItemId 
    # @yield 
    # @return [void]
    def process_instance_work_item(containerName=nil,processInstanceId=nil,workItemId=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && workItemId.class == Fixnum && block_given?
        return @j_del.java_method(:processInstanceWorkItem, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,workItemId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil, ar.succeeded ? ar.result != nil ? JSON.parse(ar.result.toJson.encode) : nil : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_work_item(containerName,processInstanceId,workItemId)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @param [Fixnum] workItemId 
    # @yield 
    # @return [void]
    def process_instance_abort_work_item(containerName=nil,processInstanceId=nil,workItemId=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && workItemId.class == Fixnum && block_given?
        return @j_del.java_method(:processInstanceAbortWorkItem, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,workItemId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_abort_work_item(containerName,processInstanceId,workItemId)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @param [Fixnum] workItemId 
    # @yield 
    # @return [void]
    def process_instance_complete_work_item(containerName=nil,processInstanceId=nil,workItemId=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && workItemId.class == Fixnum && block_given?
        return @j_del.java_method(:processInstanceCompleteWorkItem, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,workItemId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_complete_work_item(containerName,processInstanceId,workItemId)"
    end
    # @param [String] containerName 
    # @param [Fixnum] processInstanceId 
    # @param [String] signalName 
    # @param [Hash{String => Object}] signalPayload 
    # @yield 
    # @return [void]
    def process_instance_signal(containerName=nil,processInstanceId=nil,signalName=nil,signalPayload=nil)
      if containerName.class == String && processInstanceId.class == Fixnum && signalName.class == String && signalPayload.class == Hash && block_given?
        return @j_del.java_method(:processInstanceSignal, [Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,processInstanceId,signalName,::Vertx::Util::Utils.to_json_object(signalPayload),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instance_signal(containerName,processInstanceId,signalName,signalPayload)"
    end
    # @param [String] containerName 
    # @param [String] signalName 
    # @param [Hash{String => Object}] signalPayload 
    # @yield 
    # @return [void]
    def process_instances_signal(containerName=nil,signalName=nil,signalPayload=nil)
      if containerName.class == String && signalName.class == String && signalPayload.class == Hash && block_given?
        return @j_del.java_method(:processInstancesSignal, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(containerName,signalName,::Vertx::Util::Utils.to_json_object(signalPayload),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling process_instances_signal(containerName,signalName,signalPayload)"
    end
  end
end
