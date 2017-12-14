//package tip;
//
//import cucumber.api.cli.Main;
//import org.junit.rules.ExternalResource;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//
//public class ServerRule extends ExternalResource{
//
//    private final int port;
//    ConfigurableApplicationContext app;
//
//    public ServerRule(int port) {
//        this.port = port;
//    }
//
//    @Override
//    protected void before() throws Throwable {
//        this.app = SpringApplication.run(Main.class);
//    }
//
//    @Override
//    protected void after() {
//        app.close();
//    }
//}
