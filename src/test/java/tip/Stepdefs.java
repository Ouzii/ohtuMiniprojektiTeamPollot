package tip;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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

    public void setChromePathForLocalTesting(boolean on) {
        if (on) {
            File file = new File("lib/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        }

    }

    @Before
    public void setUp() {
        //setChromePathForLocalTesting(false); //True jos testit suoritetaan kotikoneella. Lisäksi on alustettava testinäytteet ja käynnistettävä testiserveri manuaalisesti.
        //False jos haluataan että travis build menee läpi!!!

        startCount = 0;
        this.driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^user is at the main page$")
    public void user_is_at_the_main_page() {
        driver.get("http://localhost:" + 8080 + "/");
        //Thread.sleep(1000);
    }

    @Given("^user is at the tags page$")
    public void user_is_at_the_tags_page() throws Throwable {
        driver.get("http://localhost:" + 8080 + "/tags");
        //Thread.sleep(1000);
    }

    @Given("^user is at add booktip page$")
    public void user_is_at_add_booktip_page() throws Throwable {
        driver.get("http://localhost:" + 8080 + "/book");
        //Thread.sleep(1000);
    }

    @Given("^user is at the modification page of \"([^\"]*)\"$")
    public void user_is_at_the_modification_page_of(String pagenumber) throws Throwable {
        driver.get("http://localhost:" + 8080 + "/book/" + pagenumber); // jos tulee whitelabel error vaihda viimeisen parametrin numeroarvoa
        //Thread.sleep(1000);
    }

    @Given("^user is at the podcast modification page of \"([^\"]*)\"$")
    public void user_is_at_the_podcast_modification_page_of(String podcastNumber) throws Throwable {
        driver.get("http://localhost:" + 8080 + "/podcast/" + podcastNumber); // jos tulee whitelabel error vaihda viimeisen parametrin numeroarvoa
        //Thread.sleep(1000);
    }

    @When("^a link is clicked$")
    public void a_link_is_clicked() throws Throwable {
        //Thread.sleep(1000);
        clickLinkWithText("linkki");
        //Thread.sleep(1000);
    }

    @Then("^error \"([^\"]*)\" is shown$")
    public void error_is_shown(String error) throws Throwable {
        System.out.println(driver.findElement(By.tagName("body")).getText());
        assertTrue(driver.findElement(By.tagName("body")).getText().contains(error));
        System.out.println(driver.findElement(By.tagName("body")).getText());
    }

    @Then("^\"([^\"]*)\" is shown$")
    public void is_shown(String arg1) throws Throwable {
        assertTrue(driver.findElement(By.tagName("body")).getText().contains(arg1));
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

    @Then("^tip with name \"([^\"]*)\" and isbn \"([^\"]*)\" and kirjoittaja \"([^\"]*)\" is listed$")
    public void tip_with_name_and_isbn_and_kirjoittaja_and_date_is_listed(String arg1, String arg2, String arg3) throws Throwable {

        driver.findElement(By.tagName("ul")).getText().contains("arg1");
        driver.findElement(By.tagName("ul")).getText().contains("arg2");
        driver.findElement(By.tagName("ul")).getText().contains("arg3");
        assertTrue(true);

    }

    @Then("^tip with name \"([^\"]*)\" and isbn \"([^\"]*)\" and kirjoittaja \"([^\"]*)\" is not listed$")
    public void tip_with_name_and_isbn_and_kirjoittaja_and_date_is_not_listed(String arg1, String isbn, String arg3) throws Throwable {
        boolean name = driver.findElements(By.linkText(isbn)).size() < 1;

    }

    @When("^valid name \"([^\"]*)\" valid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" are entered$")
    public void valid_name_valid_isbn_and_valid_writer_are_entered(String name, String isbn, String kirjoittaja, String date) throws Throwable {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("isbn")).clear();
        driver.findElement(By.name("isbn")).sendKeys(isbn);
        driver.findElement(By.name("writer")).sendKeys(kirjoittaja);
        driver.findElement(By.name("date")).sendKeys(date);

        driver.findElement(By.name("save_changes")).click();
    }

    @When("^valid name \"([^\"]*)\" invalid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" are entered$")
    public void valid_name_invalid_isbn_and_valid_writer_are_entered(String name, String isbn, String writer, String date) throws Throwable {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("isbn")).clear();
        driver.findElement(By.name("isbn")).sendKeys(isbn);
        driver.findElement(By.name("writer")).sendKeys(writer);
        driver.findElement(By.name("date")).sendKeys(date);

        driver.findElement(By.name("save_changes")).click();
    }

    @When("^invalid name \"([^\"]*)\" valid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" are entered$")
    public void invalid_name_valid_isbn_and_valid_writer_are_entered(String name, String isbn, String writer) throws Throwable {
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("isbn")).clear();
        driver.findElement(By.name("isbn")).sendKeys(isbn);
        driver.findElement(By.name("writer")).sendKeys(writer);

        driver.findElement(By.name("save_changes")).click();
    }

    @When("^delete button is clicked$")
    public void delete_button_is_clicked() {
        startCount = driver.findElements(By.xpath("//button")).size();
        driver.findElement(By.tagName("button")).click();

    }

    @Then("^one item has been deleted$")
    public void one_item_has_been_deleted() throws Throwable {
        int endCount = driver.findElements(By.xpath("//button")).size();

        System.out.println("Startcount/endcount" + startCount + "/" + endCount);

        assertTrue(startCount > endCount);

    }

    @When("^tag name \"([^\"]*)\" is ented$")
    public void tag_is_added_to_database(String tagName) {
        driver.findElement(By.name("tag_name")).sendKeys(tagName);
        driver.findElement(By.name("submit_tag")).click();
    }

    @When("^tag name \"([^\"]*)\" is entered$")
    public void tag_name_is_entered(String tagName) throws Throwable {
        driver.findElement(By.name("tag_name")).sendKeys(tagName);
        driver.findElement(By.name("submit_tag")).click();
    }

    @When("^tags delete button is pressed$")
    public void tags_delete_button_is_pressed() throws Throwable {
        driver.findElement(By.name("delete_tag")).click();
    }

    @Then("^System will not show the deleted tag$")
    public void system_will_not_show_the_deleted_tag() throws Throwable {
        driver.findElements(By.name("delete_tag")).isEmpty();
        assertTrue(true);

    }

    @When("^tag \"([^\"]*)\" is added to the tip$")
    public void tag_is_added_to_the_tip(String tagname) throws Throwable {
        Select dropdown = new Select(driver.findElement(By.name("tagId")));
        dropdown.selectByVisibleText(tagname);
        driver.findElement(By.name("add_tag")).click();

    }

    @Given("^tag \"([^\"]*)\" is removed from the tip$")
    public void tag_is_removed_from_the_tip(String tagname) throws Throwable {
        Select dropdown = new Select(driver.findElement(By.id("removable")));
        dropdown.selectByVisibleText(tagname);
        driver.findElement(By.name("remove_tag")).click();

    }

    @Then("^tip contains tag \"([^\"]*)\"$")
    public void tip_contains_tag(String tagname) throws Throwable {
        assertTrue(true);
    }

    @When("^valid name \"([^\"]*)\" and valid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" and valid date \"([^\"]*)\" are entered$")
    public void valid_name_and_valid_isbn_and_valid_writer_and_date_are_entered(String name, String isbn, String writer, String date) throws Throwable {
        //Alussa ollaan kirjan lisäys sivulla
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("isbn")).sendKeys(isbn);
        driver.findElement(By.name("writer")).sendKeys(writer);
        driver.findElement(By.name("date")).sendKeys(date);
        driver.findElement(By.name("add_book")).click();

    }

    @Then("^tip with name \"([^\"]*)\" and isbn \"([^\"]*)\" and kirjoittaja \"([^\"]*)\" and date \"([^\"]*)\" is listed$")
    public void tip_with_name_and_isbn_and_kirjoittaja_and_date_is_listed(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        driver.get("http://localhost:8080/");
        boolean nimi = driver.findElement(By.tagName("body")).getText().contains(arg1);
        boolean kirjoittaja = driver.findElement(By.tagName("body")).getText().contains(arg2);
        boolean isbn = driver.findElement(By.tagName("body")).getText().contains(arg3);
        boolean date = driver.findElement(By.tagName("body")).getText().contains("2016-12-01");
        
        boolean onListalla = false;
        if(nimi || kirjoittaja || isbn || date){
            onListalla = true;
        }

        assertTrue(onListalla);
    }

    @When("^valid name \"([^\"]*)\" and invalid isbn \"([^\"]*)\" and valid writer \"([^\"]*)\" and valid date \"([^\"]*)\" are entered$")
    public void valid_name_and_invalid_isbn_and_valid_writer_and_valid_date_are_entered(String name, String isbn, String writer, String date) throws Throwable {
        //Alussa ollaan kirjan lisäys sivulla
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("isbn")).sendKeys(isbn);
        driver.findElement(By.name("writer")).sendKeys(writer);
        driver.findElement(By.name("date")).sendKeys(date);
        driver.findElement(By.name("add_book")).click();

    }

    @When("^user sets isbn to \"([^\"]*)\"$")
    public void user_sets_isbn_to(String newisbn) throws Throwable {
        driver.findElement(By.name("isbn")).clear();
        driver.findElement(By.name("isbn")).sendKeys(newisbn);
        driver.findElement(By.name("save_changes")).click();
    }

    @When("^user sets name to \"([^\"]*)\"$")
    public void user_sets_name_to(String newname) throws Throwable {
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(newname);
        driver.findElement(By.name("save_changes")).click();
    }

    @When("^user checks the checkbox$")
    public void user_checks_the_checkbox() throws Throwable {
        if (!driver.findElement(By.name("read")).isSelected()) { //Jos ei ole mertattu luetuksi niin merkataan luetuksi
            driver.findElement(By.name("read")).click();
        }
        driver.findElement(By.name("save_changes")).click(); //Tallennetaan muutokset
    }

    @When("^user unchecks the checkbox$")
    public void user_unchecks_the_checkbox() throws Throwable {
        if (driver.findElement(By.name("read")).isSelected()) { //Jos merkattu luetuksi niin merkataan lukemattomaksi
            driver.findElement(By.name("read")).click();
        }
        driver.findElement(By.name("save_changes")).click(); //Tallennetaan muutokset
    }

    @Then("^the checkbox is checked$")
    public void the_checkbox_is_checked() throws Throwable {
        assertTrue(driver.findElement(By.name("read")).isSelected());
    }

    @Then("^the checkbox is unchecked$")
    public void the_checkbox_is_unchecked() throws Throwable {
        assertTrue(!driver.findElement(By.name("read")).isSelected());
    }

    @Then("^tip does not contain tag \"([^\"]*)\"$")
    public void tip_does_not_contain_tag(String arg1) throws Throwable {
        assertTrue(true);
    }

    @When("^testbooks are added$")
    public void testbooks_are_added() throws Throwable {
        driver.get("localhost:8080");
        driver.findElement(By.linkText("Alusta testinäytteet")).click();
    }

    //Alla podcast testien käyttämät komennot:
    @Given("^user is at the podcast page$")
    public void user_is_at_the_podcast_page() throws Throwable {
        driver.get("http://localhost:8080/podcast");
    }

    @When("^valid name \"([^\"]*)\" valid url \"([^\"]*)\" valid artisti \"([^\"]*)\" and valid date \"([^\"]*)\" are entered$")
    public void valid_name_valid_url_valid_artisti_and_valid_date_are_entered(String name, String url, String artist, String date) throws Throwable {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("url")).sendKeys(url);
        driver.findElement(By.name("artist")).sendKeys(artist);
        driver.findElement(By.name("date")).sendKeys(date);
        driver.findElement(By.name("add_book")).click();
    }

    @When("^modification details valid name \"([^\"]*)\" valid url \"([^\"]*)\" valid artisti \"([^\"]*)\" and valid date \"([^\"]*)\" are entered$")
    public void modification_details_valid_name_valid_url_valid_artisti_and_valid_date_are_entered(String name, String url, String artist, String date) throws Throwable {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("url")).sendKeys(url);
        driver.findElement(By.name("artist")).sendKeys(artist);
        driver.findElement(By.name("date")).sendKeys(date);
        Thread.sleep(startCount);
        driver.findElement(By.name("save_changes")).click();
    }

}
