package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import ohtu.cli.App;
import ohtu.cli.io.StubIO;
import ohtu.service.AppController;
import ohtu.service.Database;
import ohtu.service.Tip;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;

public class Stepdefs {

    App app;
    StubIO io;
    WebDriver driver;
    List<String> inputLines = new ArrayList<>();
    AppController controller = new AppController();
    Database db;

    public Stepdefs() throws Exception {
        db = new Database("jdbc:sqlite:testi.db");
    }

    @After
    public void tearDown() {
//        System.setOut(null);
//        System.setErr(null);
    }

    @Given("^command lisaa is selected$")
    public void command_lisaa_is_selected() throws Throwable {

        inputLines.add("lisaa");
        io = new StubIO(inputLines);
        app = new App(io, controller);
    }

    @When("^a valid name \"([^\"]*)\" and type \"([^\"]*)\" are entered$")
    public void a_valid_name_and_type_are_entered(String name, String type) throws Throwable {
        inputLines.add(name);
        inputLines.add(type);

        io = new StubIO(inputLines);
        app = new App(io, controller);

    }

    @Then("^a new reading tip is added to the database$")
    public void a_new_reading_tip_is_added_to_the_database() throws Throwable {
        ArrayList<Tip> rivit = controller.getTips();

        boolean loytyy = false;

        for (Tip tip : rivit) {
            if (tip.getName().equals("Tekniikan Maailma 2017/2 sivu 3.")) {
                loytyy = true;
            }

        }
        Assert.assertEquals(true, loytyy);

    }

    @Given("^command listaa is selected$")
    public void command_listaa_is_selected() throws Throwable {
        inputLines.add("listaa");
        inputLines.add("lopeta");

        io = new StubIO(inputLines);
        app = new App(io, controller);
        
        app.run();
        
    }

    @Then("^all reading tips get printed to console$")
    public void all_reading_tips_get_printed_to_console() throws Throwable {
        //System.out.println(io.getPrints()); Tulostaa kaikki IO:n tallentamat rivit.
        Assert.assertEquals(true, io.getPrints().toString().contains(" Tekniikan Maailma 2017/2 sivu 3. type: Lehti"));
    }

}
