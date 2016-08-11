#[jBPM](http://www.jbpm.org/)/[Drools](http://www.drools.org/) and [JBoss BPMS/BRMS](http://www.jboss.org/products/bpmsuite/overview/) support for Eclipse [Vert.x](http://vertx.io) 3.*
Provides support for starting and managing BPM processes from Vert.x using the kie-server-client

The Drools/JBoss kie-server-client provides a way to interact with the [KIE Execution Server](http://www.drools.org/download/download.html)
which is the stand-alone runtime for Drools and JBoss BxMS.

With this module, you can:
* Inspect process definitions
* Start processes
* Interact with human tasks
* Inspect process instances
* Signal process instances


##Maven Dependency

```
<dependency>
    <groupId>com.diabolicallabs</groupId>
    <artifactId>vertx-kieserver-client</artifactId>
    <version>0.9</version>
</dependency>
```

**Version 0.9 does not yet have all of the expected features. Be aware that the API may
change a bit before 1.0 is released.**

##Runtime Requirements
To use this module, you must have a stand-alone running KIE Execution Server instance. The server
 isn't part of this Vert.x module. 
 
Installation and configuration instructions for the KIE Execution Server can 
be found [here](http://docs.jboss.org/drools/release/6.4.0.Final/drools-docs/html/pt06.html).

There is also an example of a docker container [here](./docker-dir)

##Configuration

The com.diabolicallabs.kie.server.Verticle expects a configuration like the below.

```
{
  "kie_server" : {
    "host" : <string>,
    "port" : <integer>,
    "use_ssl" : <boolean>,
    "user" : <string>,
    "password" : <string>
  }
}
```
*host* is the host running the KIE Server
*port* is the port the KIE Server is listening on
*use_ssl* indicates if SSL is in use
*user* is the user ID that will be used to connect to the KIE Server
*password* is the password of that user

##Configuration Example
```
{
  "kie_server" : {
    "host" : "localhost",
    "port" : 8235,
    "use_ssl" : false,
    "user" : "kieserver",
    "password" : "kieserver1!"
  }
}
```

##Service Interfaces

There are two service interfaces that have regular and reactive (Rx) versions.

##com.diabolicallabs.kie.server.service.KieServerClientService

This interface is used to manage containers and processes. Get an instance of the service proxy like this:
```
KieServerClientService service = KieServerClientService.createProxy(vertx), KieServerClientService.DEFAULT_ADDRESS);
```


##com.diabolicallabs.kie.server.service.KieServerClientTaskService

This interface is used to manage user tasks defined in processes. Get an instance of the service proxy like this:
```
KieServerClientTaskService service = KieServerClientTaskService.createProxy(vertx), KieServerClientTaskService.DEFAULT_ADDRESS);
```

##Deploy a Process Application to the KIE Server

```
  String containerName = "process.container.v1";
  
  GAV gav = new GAV("com.diabolicallabs", "VertxKieServerClientTest", "1.0");
  
  service.createContainer(containerName, PROJECT_GAV, handler -> {
    KieContainer container = handler.result();
    String containerId = container.id;
  });
```

The container name is a unique name that a process application is deployed into on the KIE Server. It isolates
applications so that you can have multiple versions running on the same server. The GAV is a Maven Group ID, Artifact ID and Version.
The artifact needs to exist on the Maven server or location that the Kie Server is pointing to. See the JBoss documentation
on how to configure the KIE Server.

The result will be a KieContainer instance.

**Event Bus version**
```
  DeliveryOptions options = new DeliveryOptions().addHeader("action", "createContainer");
  
  JsonObject gav = new JsonObject()
    .put("group-id", "com.diabolicallabs")
    .put("artifact-id", "VertxKieServerClientTest")
    .put("version", "1.0");
    
  JsonObject message = new JsonObject().put("name", "process.container.v1").put("gav", gav);
  
  vertx.eventBus().sendObservable("kie.server.client.service", message, options, handler -> {
    JsonObject result = (JsonObject) handler.result();
    KieContainer container = new KieContainer(result);
  });
```

##Get process definitions

```
service.processDefinitions(handler -> {
  List<KieProcessDefinition> definitions = handler.result();
});
```
This call will return a list of KieProcessDefinitions that you can then get the container and process ID's from.

**Event Bus version**

```
  DeliveryOptions options = new DeliveryOptions().addHeader("action", "processDefinitions");
  vertx.eventBus().sendObservable("kie.server.client.service", null, options, handler -> {
    JsonArray definitions = handler.result();
  }
```

##Start a Processes on the KIE Server

```
  String containerName = "process.container.v1";
  String processId = "VertxKieServerClientTest.KieServerClientTest";
  
  JsonObject vars = new JsonObject().put("aString", "something").put("aBoolean", true);
  
  service.startProcess(containerName, processId, vars, handler -> {
    Long processId = handler.result();
  });
```
The containerName is the name you gave the container the process was deployed to as above.
The processId is the name given the process definition in the BPMN model.
Once the process is started, the KIE Server will return the process instance ID as a Long. This ID 
can be used to interact with the process.

**Event Bus version**
```
  DeliveryOptions options = new DeliveryOptions().addHeader("action", "startProcess");
    
  JsonObject vars = new JsonObject().put("aString", "something").put("aBoolean", true);
  
  JsonObject message = new JsonObject()
  .put("containerName", "process.container.v1")
  .put("processId", "VertxKieServerClientTest.KieServerClientTest")
  .put("vars", vars);
  
  vertx.eventBus().send("kie.server.client.service", message, options, handler -> {
    Long processInstanceId = handler.result();
  }
```

##Signal a running process

```
  String containerName = "process.container.v1";
  Long processInstanceId; //Returned from service.startProcess(...)
  JsonObject signalPayload = new JsonObject().put("aString", "something").put("aBoolean", true);
  
  service.processInstanceSignal(containerName, processInstanceId, "SignalName", signalPayload, handler -> {
    //Signal sent
  });
```
Sends a signal to a running process.
The containerName is the name you gave the container the process was deployed to as above.
The processInstanceId is a Long that was returned by the startProcess call. The signalPayload
is a json object that contains any information that the signal is expecting.

**Event Bus version**
```
  DeliveryOptions options = new DeliveryOptions().addHeader("action", "processInstanceSignal");
    
  Long processInstanceId; //Returned from service.startProcess(...)
  JsonObject signalPayload = new JsonObject().put("aString", "something").put("aBoolean", true);
  
  JsonObject message = new JsonObject()
  .put("containerName", "process.container.v1")
  .put("processInstanceId", processInstanceId)
  .put("signalName", "SignalName")
  .put("signalPayload", signalPayload);
  
  vertx.eventBus().send("kie.server.client.service", message, options, handler -> {
  }
```

##Get list of work items
```
  String containerName = "process.container.v1";
  Long processInstanceId; //Returned from service.startProcess(...)
  
  service.processInstanceWorkItems(CONTAINER_NAME, processInstanceId, handler -> {
    List<KieWorkItemInstance> items = handler.result();
  });
```
The containerName is the name you gave the container the process was deployed to as above.
The processInstanceId is a Long that was returned by the startProcess call. The handler will return
a list of work items for the process instance ID. Each work item will have an ID that can be used to claim the work
item.

**Event Bus version**
```
  DeliveryOptions options = new DeliveryOptions().addHeader("action", "processInstanceWorkItems");
    
  Long processInstanceId; //Returned from service.startProcess(...)
  
  JsonObject message = new JsonObject()
  .put("containerName", "process.container.v1")
  .put("processInstanceId", processInstanceId);
  
  vertx.eventBus().send("kie.server.client.service", message, options, handler -> {
    JsonArray items = handler.result();
  }
```

##Claim a work item
```
  String credential = Credential.encode("someUser", "somePassword");
  String containerName = "process.container.v1";
  Long workItemId; //Returned from service.processInstanceWorkItems(...) or processInstanceWorkItem
  
  taskService.taskClaim(credential, containerName, processInstanceId, handler -> {
    //Task will be claimed
  });
```
The credential is an encoded credential for the user that will claim the work item. 
The containerName is the name you gave the container the process was deployed to as above.
The workItemId is a Long that was returned by the processInstanceWorkItem call. The result will be that the task
is claimed by the user represented by the credential.

**Event Bus version**
```
  DeliveryOptions options = new DeliveryOptions().addHeader("action", "taskClaim");
    
  String credential = Credential.encode("someUser", "somePassword");
  Long workItemId; //Returned from service.processInstanceWorkItems(...) or processInstanceWorkItem
  
  JsonObject message = new JsonObject()
  .put("encodedCredential", credential)
  .put("containerName", "process.container.v1")
  .put("taskId", workItemId);
  
  vertx.eventBus().send("kie.server.client.task.service", message, options, handler -> {
  }
```

##Complete a work item
```
  String credential = Credential.encode("someUser", "somePassword");
  String containerName = "process.container.v1";
  Long workItemId; //Returned from service.processInstanceWorkItems(...) or processInstanceWorkItem
  JsonObject taskPayload = new JsonObject().put("aString", "something").put("aBoolean", true);
  
  taskService.taskComplete(credential, CONTAINER_NAME, 1l, taskPayload, handler -> {
    //Task completed
  });
```
The credential is an encoded credential for the user that will claim the work item. 
The containerName is the name you gave the container the process was deployed to as above.
The workItemId is a Long that was returned by the processInstanceWorkItem call. The taskPayload is a json object
 that will set the variables, if any, that will be set at the completion of the task. The result will be that the task
is completed.

**Event Bus version**
```
  DeliveryOptions options = new DeliveryOptions().addHeader("action", "taskComplete");
    
  String credential = Credential.encode("someUser", "somePassword");
  Long workItemId; //Returned from service.processInstanceWorkItems(...) or processInstanceWorkItem
  
  JsonObject taskPayload = new JsonObject().put("aString", "something").put("aBoolean", true);
  
  JsonObject message = new JsonObject()
  .put("encodedCredential", credential)
  .put("containerName", "process.container.v1")
  .put("taskId", workItemId)
  .put("variables", taskPayload;
  
  vertx.eventBus().send("kie.server.client.task.service", message, options, handler -> {
  }
```