package test.diabolicallabs.kieserver.client;


import com.diabolicallabs.kie.server.Credential;
import com.diabolicallabs.kie.server.Verticle;
import com.diabolicallabs.kie.server.model.GAV;
import com.diabolicallabs.kie.server.rxjava.service.KieServerClientService;
import com.diabolicallabs.kie.server.rxjava.service.KieServerClientTaskService;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class KieServerClientTaskTest {

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
        .put("host", "192.168.99.100")
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
  public void testWorkItemClaim(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskClaimObservable(credential, CONTAINER_NAME, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );


  }

  @Test
  public void testWorkItemRelease(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskClaimObservable(credential, CONTAINER_NAME, workItem.id);
      }, (workItem, nothing) -> workItem)
      .flatMap(workItem -> {
        return taskService.taskReleaseObservable(credential, CONTAINER_NAME, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

  @Test
  public void testWorkItemStart(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskStartObservable(credential, CONTAINER_NAME, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

  @Test
  public void testWorkItemSkip(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskSkipObservable(credential, CONTAINER_NAME, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

  @Test
  public void testWorkItemStop(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskStartObservable(credential, CONTAINER_NAME, workItem.id);
      }, (workItem, nothing) -> workItem)
      .flatMap(workItem -> {
        return taskService.taskStopObservable(credential, CONTAINER_NAME, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

  @Test
  public void testWorkItemComplete(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskStartObservable(credential, CONTAINER_NAME, workItem.id);
      }, (workItem, nothing) -> workItem)
      .flatMap(workItem -> {
        return taskService.taskCompleteObservable(credential, CONTAINER_NAME, workItem.id, new JsonObject());
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

  @Test
  public void testWorkItemDelegate(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskClaimObservable(credential, CONTAINER_NAME, workItem.id);
      }, (workItem, nothing) -> workItem)
      .flatMap(workItem -> {
        return taskService.taskDelegateObservable(credential, CONTAINER_NAME, workItem.id, "iomaka");
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

  @Test
  public void testWorkItemFail(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskStartObservable(credential, CONTAINER_NAME, workItem.id);
      }, (workItem, nothing) -> workItem)
      .flatMap(workItem -> {
        return taskService.taskFailObservable(credential, CONTAINER_NAME, workItem.id);
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

  @Test
  public void testWorkItemForward(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientService.DEFAULT_ADDRESS);
    KieServerClientTaskService taskService = KieServerClientTaskService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kie.server.service.KieServerClientTaskService.DEFAULT_ADDRESS);

    String credential = Credential.encode("ioniki", "kieserver1!");

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
      .flatMap(workItem -> {
        return taskService.taskClaimObservable(credential, CONTAINER_NAME, workItem.id);
      }, (workItem, nothing) -> workItem)
      .flatMap(workItem -> {
        return taskService.taskForwardObservable(credential, CONTAINER_NAME, workItem.id, "iomaka");
      })
      .toList()
      .subscribe(
        list -> context.assertTrue(list.size() > 0),
        context::fail,
        async::complete
      );
  }

}
