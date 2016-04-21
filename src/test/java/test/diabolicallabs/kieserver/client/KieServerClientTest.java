package test.diabolicallabs.kieserver.client;


import com.diabolicallabs.kieserver.rxjava.client.KieServerClientService;
import com.diabolicallabs.kieserver.client.Verticle;
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

@RunWith(io.vertx.ext.unit.junit.VertxUnitRunner.class)
public class KieServerClientTest {

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void before(TestContext context) {

    JsonObject config = new JsonObject();
    config.put("kie_server",
      new JsonObject()
        .put("host", "localhost")
        .put("port", 8230)
        .put("use_ssl", false)
        .put("user", "kieserver")
        .put("password", "kieserver1!")
    );
    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    rule.vertx().deployVerticle(Verticle.class.getName(), options, context.asyncAssertSuccess(id -> {
      System.out.println("Verticle deployment id: " + id);
    }));
  }


  @Test
  public void testListNaics(TestContext context) {

    Async async = context.async();

    KieServerClientService service = KieServerClientService.createProxy(new Vertx(rule.vertx()), com.diabolicallabs.kieserver.client.KieServerClientService.DEFAULT_ADDRESS);

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

}
