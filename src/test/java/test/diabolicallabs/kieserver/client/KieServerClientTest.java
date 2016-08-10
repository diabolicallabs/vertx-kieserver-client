package test.diabolicallabs.kieserver.client;


import com.diabolicallabs.kie.server.Verticle;
import com.diabolicallabs.kie.server.model.GAV;
import com.diabolicallabs.kie.server.model.KieWorkItemInstance;
import com.diabolicallabs.kie.server.rxjava.service.KieServerClientService;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.rxjava.core.Vertx;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Observable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class KieServerClientTest {

  private static final String GROUP_NAME = "com.diabolicallabs";
  private static final String PROJECT_NAME = "VertxKieServerClientTest";
  private static final String VERSTION_NAME = "1.0";
  private static final GAV PROJECT_GAV = new GAV(GROUP_NAME, PROJECT_NAME, VERSTION_NAME);

  private static final String PROCESS_ID = "VertxKieServerClientTest.KieServerClientTest";
  private static final String CONTAINER_NAME = "vertx";

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    JsonObject config = new JsonObject();
    config.put("kie_server",
      new JsonObject()
        .put("host", "localhost")
        .put("port", 8080)
        .put("use_ssl", false)
        .put("user", "kieserver")
        .put("password", "kieserver1!")
    );
    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    rule.vertx().deployVerticle(Verticle.class.getName(), options, context.asyncAssertSuccess(id -> {
      System.out.println("Verticle deployment id: " + id);
    }));
  }

  private Observable<List<Void>> deleteAllContainersObservable(KieServerClientService service) {

    return service.containersObservable()
      .flatMap(Observable::from)
      .map(kieContainer -> kieContainer.id)
      .flatMap(id -> {
        return service.processInstancesObservable()
          .flatMap(Observable::from)
          .flatMap(kieProcessInstance -> {
            return service.deleteProcessInstanceObservable(CONTAINER_NAME, kieProcessInstance.processInstanceId);
          })
          .toList();
      }, (id, two) -> id)
      .flatMap(service::deleteContainerObservable)
      .toList();
  }

  @Test
  public void testInfo(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    service.informationObservable()
      .doOnNext(info -> {
        System.out.println(info.toJson().encodePrettily());
      })
      .subscribe(
        kieServerInformation -> {
          context.assertTrue(true);
        },
        context::fail,
        async::complete
      );

  }

  @Test
  public void testDeleteAllContainers(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .subscribe(
        nothing -> { context.assertTrue(true); },
        context::fail,
        async::complete
      );

  }

  @Test
  public void testCreateContainer(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .doOnNext(container -> {
        System.out.println("Container: " + container.toJson().encodePrettily());
      })
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );

  }

  @Test
  public void testDeleteContainer(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .doOnNext(container -> {
        System.out.println("Container: " + container.toJson().encodePrettily());
      })
      .flatMap(kieContainer -> {
        return service.deleteContainerObservable(CONTAINER_NAME);
      })
      .subscribe(
        kieServerInformation -> {
          context.assertTrue(true);
        },
        context::fail,
        async::complete
      );

  }

  @Test
  public void testGetContainer(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .doOnNext(container -> {
        System.out.println("Container: " + container.toJson().encodePrettily());
      })
      .flatMap(kieContainer -> {
        return service.containerObservable(CONTAINER_NAME);
      })
      .doOnNext(container -> {
        System.out.println(container.toJson().encodePrettily());
      })
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListContainers(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.containersObservable();
      })
      .doOnNext(list -> {
        System.out.println(list);
        context.assertTrue(list.size() > 0);
      })
      .flatMap(Observable::from)
      .subscribe(
        container -> {
          System.out.println(container.toJson().encodePrettily());
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListProcessDefinitions(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .doOnNext(kieContainer -> {
        System.out.println("Container created: " + kieContainer.toJson().encodePrettily());
      })
      .flatMap(kieContainer -> {
        return service.processDefinitionsObservable();
      })
      .flatMap(Observable::from)
      .doOnNext(definition -> {
        System.out.println("Definition: " + definition.toJson().encodePrettily());
      })
      .toList()
      .subscribe(
        list -> {
          context.assertTrue(list.size() > 0);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListProcessVariables(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.processVariableDefinitionsObservable(CONTAINER_NAME, PROCESS_ID);
      })
      .doOnNext(definition -> {
        System.out.println("Definition: " + definition.encodePrettily());
      })
      .subscribe(
        definition -> {
          context.assertNotNull(definition);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListServiceTaskDefinitions(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.processServiceTaskDefinitionsObservable(CONTAINER_NAME, PROCESS_ID);
      })
      .doOnNext(definition -> {
        System.out.println("Definition: " + definition.encodePrettily());
      })
      .subscribe(
        definition -> {
          context.assertNotNull(definition);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListUserTaskDefinitions(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.processUserTaskDefinitionsObservable(CONTAINER_NAME, PROCESS_ID);
      })
      .flatMap(Observable::from)
      .doOnNext(definition -> {
        System.out.println("Definition: " + definition.toJson().encodePrettily());
      })
      .toList()
      .subscribe(
        list -> {
          context.assertTrue(list.size() > 0);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListSubprocessDefinitions(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.processSubprocessDefinitionsObservable(CONTAINER_NAME, PROCESS_ID);
      })
      .flatMap(Observable::from)
      .doOnNext(name -> {
        System.out.println("Name: " + name);
      })
      .toList()
      .subscribe(
        list -> {
          context.assertTrue(list.size() > 0);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListUserTaskInputDefinitions(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.processUserTaskInputDefinitionsObservable(CONTAINER_NAME, PROCESS_ID, "DoSomeWork");
      })
      .subscribe(
        inputs -> {
          context.assertTrue(inputs.size() > 0);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListUserTaskOutputDefinitions(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.processUserTaskOutputDefinitionsObservable(CONTAINER_NAME, PROCESS_ID, "DoSomeWork");
      })
      .subscribe(
        outputs -> {
          context.assertTrue(outputs.size() > 0);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testListEntityDefinitions(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        return service.processEntityDefinitionsObservable(CONTAINER_NAME, PROCESS_ID);
      })
      .doOnNext(entities -> {
        System.out.println("Entities: " + entities.encodePrettily());
      })
      .subscribe(
        entities -> {
          context.assertTrue(entities.fieldNames().size() > 0);
        },
        context::fail,
        async::complete
      );

  }
  @Test
  public void testStartProcess(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose");
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );


  }
  @Test
  public void testStartProcessCorrelation(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose");
        String correlationId = UUID.randomUUID().toString();
        return service.startProcessWithCorrelationObservable(CONTAINER_NAME, PROCESS_ID, correlationId, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );

  }
  @Test
  public void testProcessInstances(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doTimer", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
        context.assertNotNull(instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstancesObservable();
      })
      .flatMap(Observable::from)
      .doOnNext(instance -> {
        System.out.println("Instance: " + instance.toJson());
      })
      .toList()
      //.delay(16, TimeUnit.SECONDS)
      .subscribe(
        list -> {
          context.assertTrue(list.size() > 0);
        },
        context::fail,
        async::complete
      );

  }

  @Test
  public void testDeleteProcessInstance(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doTimer", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.deleteProcessInstanceObservable(CONTAINER_NAME, instanceId);
      })
      .map(nothing -> true)
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );

  }

  @Test
  public void testGetProcessInstanceSignals(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doSignal", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceSignalsObservable(CONTAINER_NAME, instanceId);
      })
      .flatMap(Observable::from)
      .doOnNext(signalName -> {
        System.out.println("Signal name: " + signalName);
      })
      .toList()
      .subscribe(
        list -> {
          context.assertTrue(list.size() > 0);
        },
        context::fail,
        async::complete
      );

  }

  @Test
  public void testSignalProcessInstances(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doSignal", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstancesSignalObservable(CONTAINER_NAME, "CatchSignal", new JsonObject());
      })
      .map(nothing -> true)
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );

  }

  @Test
  public void testSignalProcessInstance(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doSignal", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceSignalObservable(CONTAINER_NAME, instanceId, "CatchSignal", new JsonObject());
      })
      .map(nothing -> true)
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );

  }

  @Test
  public void testGetProcessInstance(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doTimer", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceObservable(CONTAINER_NAME, instanceId);
      })
      .doOnNext(instance -> {
        System.out.println("Instance : " + instance.toJson().encodePrettily());
      })
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );

  }

  @Test
  public void testGetProcessInstanceVariable(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doTimer", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceVariableValueObservable(CONTAINER_NAME, instanceId, "doTimer");
      })
      .doOnNext(value -> System.out.println("Value: " + value))
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );

  }

  @Test
  public void testGetProcessInstanceWorkitems(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doHuman", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceWorkItemsObservable(CONTAINER_NAME, instanceId);
      })
      .flatMap(Observable::from)
      .doOnNext(value -> System.out.println("Work item: " + value.toJson().encodePrettily()))
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );


  }


  @Test
  public void testGetProcessInstanceWorkitem(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doHuman", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceWorkItemsObservable(CONTAINER_NAME, instanceId);
      })
      .flatMap(Observable::from)
      .flatMap(workItem -> {
        return service.processInstanceWorkItemObservable(CONTAINER_NAME, workItem.processInstanceId, workItem.id);
      })
      .doOnNext(context::assertNotNull)
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );

  }

  @Test
  public void testAbortProcessInstanceWorkitem(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doHuman", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceWorkItemsObservable(CONTAINER_NAME, instanceId);
      })
      .flatMap(Observable::from)
      .flatMap(workItem -> {
        return service.processInstanceAbortWorkItemObservable(CONTAINER_NAME, workItem.processInstanceId, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );

  }

  @Test
  public void testCompleteProcessInstanceWorkitem(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doHuman", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceWorkItemsObservable(CONTAINER_NAME, instanceId);
      })
      .flatMap(Observable::from)
      .flatMap(workItem -> {
        return service.processInstanceCompleteWorkItemObservable(CONTAINER_NAME, workItem.processInstanceId, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );

  }

  @Test
  public void testUpdateProcessInstanceVariable(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    AtomicLong processInstanceId = new AtomicLong();

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doTimer", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        processInstanceId.set(instanceId);
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceVariableValueObservable(CONTAINER_NAME, instanceId, "display");
      })
      .doOnNext(value -> System.out.println("Value: " + value))
      .flatMap(value -> {
        return service.processInstanceUpdateVariableValueObservable(CONTAINER_NAME, processInstanceId.get(), "display", "squid");
      })
      .flatMap(instanceId -> {
        return service.processInstanceVariableValueObservable(CONTAINER_NAME, processInstanceId.get(), "display");
      })
      .doOnNext(value -> System.out.println("Updated value: " + value))
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );

  }

  @Test
  public void testUpdateProcessInstanceVariables(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);

    AtomicLong processInstanceId = new AtomicLong();

    this.deleteAllContainersObservable(service)
      .flatMap(nothing -> {
        return service.createContainerObservable(CONTAINER_NAME, PROJECT_GAV);
      })
      .flatMap(kieContainer -> {
        JsonObject vars = new JsonObject().put("display", "goose").put("doTimer", true);
        return service.startProcessObservable(CONTAINER_NAME, PROCESS_ID, vars);
      })
      .doOnNext(instanceId -> {
        processInstanceId.set(instanceId);
        System.out.println("Instance Id: " + instanceId);
      })
      .flatMap(instanceId -> {
        return service.processInstanceVariableValueObservable(CONTAINER_NAME, instanceId, "display");
      })
      .doOnNext(value -> System.out.println("Value: " + value))
      .flatMap(value -> {
        JsonObject vars = new JsonObject().put("display", "squid").put("doTimer", false);
        return service.processInstanceUpdateVariablesObservable(CONTAINER_NAME, processInstanceId.get(), vars);
      })
      .flatMap(instanceId -> {
        return service.processInstanceVariableValueObservable(CONTAINER_NAME, processInstanceId.get(), "display");
      })
      .doOnNext(value -> System.out.println("Updated display value: " + value))
      .flatMap(instanceId -> {
        return service.processInstanceVariableValueObservable(CONTAINER_NAME, processInstanceId.get(), "doTimer");
      })
      .doOnNext(value -> System.out.println("Updated doTimer value: " + value))
      .subscribe(
        context::assertNotNull,
        context::fail,
        async::complete
      );

  }
}
