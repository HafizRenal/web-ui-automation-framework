package org.ui.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.ui.pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    private static final String VALID_USER   = "tomsmith";
    private static final String VALID_PASS   = "SuperSecretPassword!";
    private static final String INVALID_USER = "wronguser";
    private static final String INVALID_PASS = "wrongpass";
    private static final String LONG_USER    = "a".repeat(300);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver    = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        loginPage.open();
    }

    @When("I enter valid username and password")
    public void iEnterValidUsernameAndPassword() {
        loginPage.enterUsername(VALID_USER);
        loginPage.enterPassword(VALID_PASS);
        loginPage.clickLogin();
    }

    @When("I enter invalid username and valid password")
    public void iEnterInvalidUsernameAndValidPassword() {
        loginPage.enterUsername(INVALID_USER);
        loginPage.enterPassword(VALID_PASS);
        loginPage.clickLogin();
    }

    @When("I enter valid username and invalid password")
    public void iEnterValidUsernameAndInvalidPassword() {
        loginPage.enterUsername(VALID_USER);
        loginPage.enterPassword(INVALID_PASS);
        loginPage.clickLogin();
    }

    @When("I enter empty username and password")
    public void iEnterEmptyUsernameAndPassword() {
        loginPage.enterUsername("");
        loginPage.enterPassword(VALID_PASS);
        loginPage.clickLogin();
    }

    @When("I enter username and empty password")
    public void iEnterUsernameAndEmptyPassword() {
        loginPage.enterUsername(VALID_USER);
        loginPage.enterPassword("");
        loginPage.clickLogin();
    }

    @When("I enter a very long username")
    public void iEnterAVeryLongUsername() {
        loginPage.enterUsername(LONG_USER);
        loginPage.enterPassword(VALID_PASS);
        loginPage.clickLogin();
    }

    @Then("I should see the secure area")
    public void iShouldSeeTheSecureArea() {
        assertTrue(
                "Login gagal, URL tidak pindah ke /secure",
                loginPage.isOnSecurePage()
        );
        String flash = loginPage.getFlashMessage();
        assertTrue(
                "Pesan sukses tidak muncul. Pesan: " + flash,
                flash.contains("You logged into a secure area!")
        );
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        String flash = loginPage.getFlashMessage();
        assertTrue(
                "Pesan error tidak muncul. Pesan: " + flash,
                flash.contains("Your username is invalid!")
                        || flash.contains("Your password is invalid!")
        );
    }
}