require 'vertx/vertx'
require 'vertx/util/utils.rb'
# Generated from com.diabolicallabs.kie.server.service.KieServerClientTaskService
module Kieserver
  class KieServerClientTaskService
    # @private
    # @param j_del [::Kieserver::KieServerClientTaskService] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Kieserver::KieServerClientTaskService] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [String] address 
    # @return [::Kieserver::KieServerClientTaskService]
    def self.create_proxy(vertx=nil,address=nil)
      if vertx.class.method_defined?(:j_del) && address.class == String && !block_given?
        return ::Vertx::Util::Utils.safe_create(Java::ComDiabolicallabsKieServerService::KieServerClientTaskService.java_method(:createProxy, [Java::IoVertxCore::Vertx.java_class,Java::java.lang.String.java_class]).call(vertx.j_del,address),::Kieserver::KieServerClientTaskService)
      end
      raise ArgumentError, "Invalid arguments when calling create_proxy(vertx,address)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_activate(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskActivate, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_activate(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_claim(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskClaim, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_claim(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_start(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskStart, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_start(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_stop(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskStop, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_stop(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @param [Hash{String => Object}] variables 
    # @yield 
    # @return [void]
    def task_complete(encodedCredential=nil,containerName=nil,taskId=nil,variables=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && variables.class == Hash && block_given?
        return @j_del.java_method(:taskComplete, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCoreJson::JsonObject.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,::Vertx::Util::Utils.to_json_object(variables),(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_complete(encodedCredential,containerName,taskId,variables)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @param [String] delegateId 
    # @yield 
    # @return [void]
    def task_delegate(encodedCredential=nil,containerName=nil,taskId=nil,delegateId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && delegateId.class == String && block_given?
        return @j_del.java_method(:taskDelegate, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,delegateId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_delegate(encodedCredential,containerName,taskId,delegateId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_exit(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskExit, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_exit(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_fail(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskFail, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_fail(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @param [String] userId 
    # @yield 
    # @return [void]
    def task_forward(encodedCredential=nil,containerName=nil,taskId=nil,userId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && userId.class == String && block_given?
        return @j_del.java_method(:taskForward, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::java.lang.String.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,userId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_forward(encodedCredential,containerName,taskId,userId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_release(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskRelease, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_release(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_resume(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskResume, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_resume(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_skip(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskSkip, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_skip(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @yield 
    # @return [void]
    def task_suspend(encodedCredential=nil,containerName=nil,taskId=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && block_given?
        return @j_del.java_method(:taskSuspend, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_suspend(encodedCredential,containerName,taskId)"
    end
    # @param [String] encodedCredential 
    # @param [String] containerName 
    # @param [Fixnum] taskId 
    # @param [Array<String>] nominations 
    # @yield 
    # @return [void]
    def task_nominate(encodedCredential=nil,containerName=nil,taskId=nil,nominations=nil)
      if encodedCredential.class == String && containerName.class == String && taskId.class == Fixnum && nominations.class == Array && block_given?
        return @j_del.java_method(:taskNominate, [Java::java.lang.String.java_class,Java::java.lang.String.java_class,Java::JavaLang::Long.java_class,Java::JavaUtil::List.java_class,Java::IoVertxCore::Handler.java_class]).call(encodedCredential,containerName,taskId,nominations.map { |element| element },(Proc.new { |ar| yield(ar.failed ? ar.cause : nil) }))
      end
      raise ArgumentError, "Invalid arguments when calling task_nominate(encodedCredential,containerName,taskId,nominations)"
    end
  end
end
