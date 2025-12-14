package login;

import base.BaseSeleniumTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginSeleniumTest extends BaseSeleniumTest {

    private final String VALID_USER = "student";
    private final String VALID_PASSWORD = "Password123";

    @BeforeEach
    public void openLoginPage() {
        String loginPageUrl = "https://practicetestautomation.com/practice-test-login/";
        driver.get(loginPageUrl);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Testa login com credenciais corretas")
    public void loginValido() {
        WebElement usernameInput = setInputData(By.id("username"), VALID_USER);
        assertThat(usernameInput.getAttribute("value"), containsString(VALID_USER));

        WebElement passwordInput = setInputData(By.id("password"), VALID_PASSWORD);
        assertThat(passwordInput.getAttribute("value"), containsString(VALID_PASSWORD));

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        wait.until(ExpectedConditions.urlContains("/logged-in-successfully"));
        assertThat(driver.getCurrentUrl(), containsString("/logged-in-successfully"));

        WebElement h1 = driver.findElement(By.className("post-title"));
        assertThat(h1.getText(), containsString("Logged In Successfully"));

        WebElement paragraph = driver.findElement(By.className("has-text-align-center"));
        WebElement strong = paragraph.findElement(By.tagName("strong"));
        assertThat(strong.getText(), containsString("Congratulations student. You successfully logged in!"));

        String title = driver.getTitle();
        assertThat(title, containsString("Logged In Successfully | Practice Test Automation"));

        WebElement logOutButton = driver.findElement(By.className("wp-block-button__link"));
        assertThat(logOutButton.getText(), containsString("Log out"));
        assertThat(logOutButton.getAttribute("href"), containsString("practicetestautomation.com/practice-test-login/"));
    }

    @Test
    @DisplayName("Testa login com username incorreta")
    public void loginUsuarioInvalido() {
        String invalidUser = "estou errado KKKK";

        WebElement usernameInput = setInputData(By.id("username"), invalidUser);
        assertThat(usernameInput.getAttribute("value"), containsString(invalidUser));

        WebElement passwordInput = setInputData(By.id("password"), VALID_PASSWORD);
        assertThat(passwordInput.getAttribute("value"), containsString(VALID_PASSWORD));

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        WebElement invalidToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assertions.assertNotNull(invalidToast);
        assertThat(invalidToast.getText(), containsString("Your username is invalid!"));

        assertThat(usernameInput.getAttribute("value"), is(emptyString()));
        assertThat(passwordInput.getAttribute("value"), is(emptyString()));

        String url = driver.getCurrentUrl();
        assertThat(url, containsString("/practice-test-login/"));
    }

    @Test
    @DisplayName("Testa login com senha incorreta")
    public void loginSenhaInvalida() {
        String invalidPassword = "fala galera sou o totus to com o sombra ó";

        WebElement usernameInput = setInputData(By.id("username"), VALID_USER);
        assertThat(usernameInput.getAttribute("value"), containsString(VALID_USER));

        WebElement passwordInput = setInputData(By.id("password"), invalidPassword);
        assertThat(passwordInput.getAttribute("value"), containsString(invalidPassword));

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        WebElement invalidToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assertions.assertNotNull(invalidToast);
        assertThat(invalidToast.getText(), containsString("Your password is invalid!"));

        assertThat(usernameInput.getAttribute("value"), is(emptyString()));
        assertThat(passwordInput.getAttribute("value"), is(emptyString()));

        String url = driver.getCurrentUrl();
        assertThat(url, containsString("/practice-test-login/"));
    }

    private WebElement setInputData(By by, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Assertions.assertNotNull(element);

        element.clear();
        element.click();
        element.sendKeys(text);

        return element;
    }
}
