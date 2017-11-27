package tip;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Stepdefs {

    WebDriver driver;

    public Stepdefs() {
//        File file;
//        if (System.getProperty("os.name").matches("Mac OS X")) {
//            file = new File("lib/macgeckodriver");
//        }
//        if (System.getProperty("os.name").matches("Windows 10")) {
//            file = new File("lib/chromedriver.exe");
//        } else {
//            file = new File("lib/geckodriver");
//        }
//        String absolutePath = file.getAbsolutePath();
//        System.setProperty("webdriver.gecko.driver", absolutePath);
//        
//        if(System.getProperty("os.name").matches("Windows 10")){
//            System.setProperty("webdriver.chrome.driver", absolutePath);
//        }

        File file = new File("lib/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        this.driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
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
        System.out.println(driver.findElement(By.tagName("body")).getText()); //Koitetaan tulostaa sivun body...
        assertTrue(driver.findElement(By.tagName("body")).getText().contains(arg1));
        Thread.sleep(1000);
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

}
