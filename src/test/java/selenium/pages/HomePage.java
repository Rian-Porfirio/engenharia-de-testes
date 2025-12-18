package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private final By signupLoginButton =
            By.cssSelector("a[href='/login']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage acessarLoginSignUp() {
        click(signupLoginButton);
        return new LoginPage(driver);
    }
}
