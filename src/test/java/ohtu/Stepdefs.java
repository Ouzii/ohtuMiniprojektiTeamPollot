package ohtu;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ohtu.cli.App;
import ohtu.cli.io.StubIO;
import ohtu.service.AppController;
import ohtu.service.Collector;
import ohtu.service.Database;
import ohtu.service.Tip;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    }
//
//    @Given("^user is at the main page$")
//    public void user_is_at_the_main_page() throws Throwable {
//        driver.get("http://localhost:" + 8080 + "/");
//        Thread.sleep(1000);
//    }
//
//    @When("^a link is clicked$")
//    public void a_link_is_clicked() throws Throwable {
//        Thread.sleep(1000);
//        clickLinkWithText("linkki");
//        Thread.sleep(1000);
//    }
//
//    @Then("^\"([^\"]*)\" is shown$")
//    public void is_shown(String arg1) throws Throwable {
//        assertTrue(driver.findElement(By.tagName("body"))
//                .getText().contains(arg1));
//    }
//
//    private void clickLinkWithText(String text) {
//        int trials = 0;
//        while (trials++ < 5) {
//            try {
//                WebElement element = driver.findElement(By.linkText(text));
//                element.click();
//                break;
//            } catch (Exception e) {
//                System.out.println(e.getStackTrace());
//            }
//        }
//    }

    @Given("^command lisaa is selected$")
    public void command_lisaa_is_selected() throws Throwable {
        inputLines.add("lisaa");
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

}
