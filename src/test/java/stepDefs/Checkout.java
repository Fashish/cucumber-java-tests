package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Checkout {

    WebDriver driver;
    String depopEnv = "https://www-staging.depop.com/";

    @Given("^I am on Depop$")
    public void open_depop() {
        driver.get(depopEnv);
    }

    @Given("^I am logged in$")
    public void i_am_logged_in() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("fashinated");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div/div/div[2]/div/div/div[1]/form/div/button/span")).click();

        String logoutButton = driver.findElement(By.linkText("Logout")).getText();
        Assert.assertTrue(logoutButton.contains("Logout"));
    }

    @And("^I am on user \"(.*)\" profile page$")
    public void user_profile_page(String username) {
        driver.get(depopEnv + username);
    }

    @When("I navigate to an item of single variety")
    public void navigate_to_single_variety_item() {
        driver.get(depopEnv + "johnnydepop/johnnydepop-eileen");
    }

    @When("I can see the buy now button")
    public void verify_buy_now_button() {
        String buyNow = driver.findElement(By.xpath("//*[@id=\"mount\"]/div/div/div[3]/div/div[1]/div[2]/div[5]/div/div/a")).getText();

        Assert.assertTrue(buyNow.contains("Buy now"));
    }

    @When("I click on the buy now button")
    public void click_on_buy_now_button() {
        driver.findElement(By.xpath("//*[@id=\"mount\"]/div/div/div[3]/div/div[1]/div[2]/div[5]/div/div/a")).click();
    }

    @Then("I can see the checkout summary for the item")
    public void verify_checkout_summary() {
        String checkoutURL = driver.getCurrentUrl();
        Assert.assertTrue(checkoutURL.contains("buy-product"));
    }

    @Before
    public void setUp() {
        driver = utilities.DriverFactory.open("Chrome"); // Options: Chrome, Firefox
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
