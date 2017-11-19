package ohtu;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Stepdefs {

    WebDriver driver;

    public Stepdefs() {
    }

    @After
    public void tearDown() {
    }

    @Given("^user is at the main page$")
    public void user_is_at_the_main_page() throws Throwable {
        driver.get("http://localhost:" + 8080 + "/");
        Thread.sleep(1000);
    }

    @When("^a link is clicked$")
    public void a_link_is_clicked() throws Throwable {
        Thread.sleep(1000);
        clickLinkWithText("linkki");
        Thread.sleep(1000);
    }

    @Then("^\"([^\"]*)\" is shown$")
    public void is_shown(String arg1) throws Throwable {
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains(arg1));
    }

    private void clickLinkWithText(String text) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

    @Given("^command lisaa is selected$")
    public void command_lisaa_is_selected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^a valid name \"([^\"]*)\" and type \"([^\"]*)\" are entered$")
    public void a_valid_name_and_type_are_entered(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^a new reading tip is added to the database$")
    public void a_new_reading_tip_is_added_to_the_database() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
