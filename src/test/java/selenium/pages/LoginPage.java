package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends HomePage{

    private final By loginEmail =
            By.cssSelector("input[data-qa='login-email']");
    private final By loginPassword =
            By.cssSelector("input[data-qa='login-password']");
    private final By loginButton =
            By.cssSelector("button[data-qa='login-button']");
    private final By loginError =
            By.xpath("//form[@action='/login']//p[contains(text(), 'Your email or password is incorrect')]");

    private final By signupName =
            By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmail =
            By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton =
            By.cssSelector("button[data-qa='signup-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        type(loginEmail, email);
        type(loginPassword, password);
        click(loginButton);
    }

    public String getLoginErrorMessage() {
        return getText(loginError);
    }

    public RegisterPage signUp(String name, String email) {
        type(signupName, name);
        type(signupEmail, email);
        click(signupButton);
        return new RegisterPage(driver);
    }
}
