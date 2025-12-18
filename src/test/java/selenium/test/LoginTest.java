package selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selenium.pages.HomePage;
import selenium.pages.LoginPage;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class LoginTest extends BaseTest {

    private LoginPage page;

    @BeforeEach
    void setUp() {
        HomePage home = new HomePage(driver);
        page = home.acessarLoginSignUp();
    }

    @Test
    void deveFalharLoginComCredenciaisInvalidas() {
        page.login("email@invalido.com", "senhaerrada");
        String mensagemErro = page.getLoginErrorMessage();
        assertThat(mensagemErro, containsString("Your email or password is incorrect"));
    }

    @Test
    void deveLogarComCredenciaisValidas() {
        page.login("testeawdad@gmail.com", "123123");

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl, not(containsString("/login")));
    }
}
