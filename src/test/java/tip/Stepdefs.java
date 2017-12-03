package tip;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import java.io.File;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Stepdefs {

    WebDriver driver;
    private int startCount;
    
    @Before
    public void setUp() {
        this.driver = new ChromeDriver();
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//
//    @Given("^user is at the main page$")
//    public void user_is_at_the_main_page() throws Throwable {
//        driver.get("http://localhost:" + 8080 + "/");
//        //Thread.sleep(1000);
//    }
//    @Given("^user is at add booktip page$")
//    public void user_is_at_add_booktip_page() throws Throwable {
//        driver.get("http://localhost:" + 8080 + "/book");
//        //Thread.sleep(1000);
//    }
//    @Given("^user is at the modification page$")
//    public void user_is_at_the_modification_page() throws Throwable {
//        user_is_at_the_main_page(); //Luodaan testidataa jotta voidaan jatkaa testausta
//        valid_name_and_invalid_isbn_and_valid_kirjoittaja_are_entered("asdasd", "978-951-98548-9-2", "asdasd");
//        driver.get("http://localhost:" + 8080 + "/2?"); // jos tulee whitelabel error vaihda viimeisen parametrin numeroarvoa
//        //Thread.sleep(1000);
//    }
//    
//
//    @Given("^user is at the modification page of \"([^\"]*)\"$")
//    public void user_is_at_the_modification_page_of(String pagenumber) throws Throwable {
//        user_is_at_the_main_page(); //Luodaan testidataa jotta voidaan jatkaa testausta
//        valid_name_and_invalid_isbn_and_valid_kirjoittaja_are_entered("asdasd", "978-951-98548-9-2", "asdasd");
//        driver.get("http://localhost:" + 8080 + "/" + pagenumber + "?"); // jos tulee whitelabel error vaihda viimeisen parametrin numeroarvoa
//        //Thread.sleep(1000);
//    }
//
//    @When("^a link is clicked$")
//    public void a_link_is_clicked() throws Throwable {
//        //Thread.sleep(1000);
//        clickLinkWithText("linkki");
//        //Thread.sleep(1000);
//    }
//
//    @Then("^\"([^\"]*)\" is shown$")
//    public void is_shown(String arg1) throws Throwable {
//        System.out.println(driver.findElement(By.tagName("body")).getText()); //Koitetaan tulostaa sivun body...
//        assertTrue(driver.findElement(By.tagName("body")).getText().contains(arg1));
//        //Thread.sleep(1000);
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
//
//    @When("^valid name \"([^\"]*)\" and valid isbn \"([^\"]*)\" and valid kirjoittaja \"([^\"]*)\" are entered$")
//    public void valid_name_and_valid_isbn_and_valid_kirjoittaja_are_entered(String nimi, String isbn, String kirjoittaja) {
//
//        driver.findElement(By.name("name")).sendKeys(nimi);
//        driver.findElement(By.name("isbn")).sendKeys(nimi);
//        driver.findElement(By.name("writer")).sendKeys(nimi);
//
//        driver.findElement(By.name("add_book")).click();
//    }
//
//    @Then("^tip with name \"([^\"]*)\" and isbn \"([^\"]*)\" and kirjoittaja \"([^\"]*)\" is listed$")
//    public void tip_with_name_and_isbn_and_kirjoittaja_is_listed(String arg1, String arg2, String arg3) {
//
//        driver.findElement(By.tagName("ul")).getText().contains("arg1");
//        driver.findElement(By.tagName("ul")).getText().contains("arg2");
//        driver.findElement(By.tagName("ul")).getText().contains("arg3");
//    }
//
//    @When("^valid name \"([^\"]*)\" and invalid isbn \"([^\"]*)\" and valid kirjoittaja \"([^\"]*)\" are entered$")
//    public void valid_name_and_invalid_isbn_and_valid_kirjoittaja_are_entered(String name, String isbn, String kirjoittaja) throws Throwable {
//        driver.findElement(By.name("name")).sendKeys(name);
//        driver.findElement(By.name("isbn")).sendKeys(isbn);
//        driver.findElement(By.name("writer")).sendKeys(kirjoittaja);
//
//        driver.findElement(By.name("add_book")).click();
//    }
//
//    @Then("^tip with name \"([^\"]*)\" and isbn \"([^\"]*)\" and kirjoittaja \"([^\"]*)\" is not listed$")
//    public void tip_with_name_and_isbn_and_kirjoittaja_is_not_listed(String arg1, String isbn, String arg3) throws Throwable {
//        boolean name = driver.findElements(By.linkText(isbn)).size() < 1;
//
//    }
//
//    @When("^valid name \"([^\"]*)\" valid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" are entered$")
//    public void valid_name_valid_isbn_and_valid_writer_are_entered(String name, String isbn, String kirjoittaja) throws Throwable {
//        driver.findElement(By.name("name")).sendKeys(name);
//        driver.findElement(By.name("isbn")).clear();
//        driver.findElement(By.name("isbn")).sendKeys(isbn);
//        driver.findElement(By.name("writer")).sendKeys(kirjoittaja);
//
//        driver.findElement(By.name("save_changes")).click();
//    }
//
//    @When("^valid name \"([^\"]*)\" invalid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" are entered$")
//    public void valid_name_invalid_isbn_and_valid_writer_are_entered(String name, String isbn, String writer) throws Throwable {
//        driver.findElement(By.name("name")).sendKeys(name);
//        driver.findElement(By.name("isbn")).clear();
//        driver.findElement(By.name("isbn")).sendKeys(isbn);
//        driver.findElement(By.name("writer")).sendKeys(writer);
//
//        driver.findElement(By.name("save_changes")).click();
//    }
//
//    @When("^invalid name \"([^\"]*)\" valid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" are entered$")
//    public void invalid_name_valid_isbn_and_valid_writer_are_entered(String name, String isbn, String writer) throws Throwable {
//        driver.findElement(By.name("name")).clear();
//        driver.findElement(By.name("name")).sendKeys(name);
//        driver.findElement(By.name("isbn")).clear();
//        driver.findElement(By.name("isbn")).sendKeys(isbn);
//        driver.findElement(By.name("writer")).sendKeys(writer);
//
//        driver.findElement(By.name("save_changes")).click();
//    }
//
//    @When("^delete button is clicked$")
//    public void delete_button_is_clicked() throws Throwable {
//        user_is_at_the_main_page(); //Luodaan testidataa jotta voidaan jatkaa testausta
//        valid_name_and_invalid_isbn_and_valid_kirjoittaja_are_entered("asdasd", "978-951-98548-9-2", "asdasd");
//        //Thread.sleep(1000);
//
//        startCount = driver.findElements(By.className("li")).size();
//
//        driver.findElement(By.name("delete")).click();
//
//    }
//
//    @Then("^one item has been deleted$")
//    public void one_item_has_been_deleted() throws Throwable {
//        int endCount = driver.findElements(By.className("li")).size();
//
//        boolean onkopoistettu = startCount > endCount;
//    }
//
//    @When("^tag name \"([^\"]*)\" is ented$")
//    public void tag_is_added_to_database(String tagName) {
//        driver.findElement(By.name("tag_name")).sendKeys(tagName);
//        driver.findElement(By.name("submit_tag")).click();
//    }
//
//    @When("^tag name \"([^\"]*)\" is entered$")
//    public void tag_name_is_entered(String tagName) throws Throwable {
//        driver.findElement(By.name("tag_name")).sendKeys(tagName);
//        driver.findElement(By.name("submit_tag")).click();
//    }
//
//    @When("^tags delete button is pressed$")
//    public void tags_delete_button_is_pressed() throws Throwable {
//        driver.findElement(By.name("delete_tag"));
//    }
//
//    @Then("^System will not show the deleted tag$")
//    public void system_will_not_show_the_deleted_tag() throws Throwable {
//        driver.findElements(By.name("delete_tag")).isEmpty();
//    }
//
//    @When("^tag \"([^\"]*)\" is added to the tip$")
//    public void tag_is_added_to_the_tip(String tagname) throws Throwable {
//        Thread.sleep(10000);
//        WebElement mySelectElement = driver.findElement(By.name("tagiId"));
//        Select dropdown = new Select(mySelectElement);
//        dropdown.selectByVisibleText(tagname);
//        Thread.sleep(10000);
//        driver.findElement(By.linkText("Lisää tagi!")).click();
//        Thread.sleep(10000);
//
//    }
//
//    @Then("^tip contains tag \"([^\"]*)\"$")
//    public void tip_contains_tag(String tagname) throws Throwable {
//        //TODO
//    }

}
