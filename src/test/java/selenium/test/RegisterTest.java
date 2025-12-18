package selenium.test;

import org.junit.jupiter.api.Test;
import selenium.pages.AccountCreatedPage;
import selenium.pages.HomePage;
import selenium.pages.LoginPage;
import selenium.pages.RegisterPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RegisterTest extends BaseTest {

    @Test
    void deveCriarContaComSucesso() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.acessarLoginSignUp();

        RegisterPage registerPage = loginPage
                .signUp("teste", "testezindasilva" + System.currentTimeMillis() + "@test.com");

        registerPage.selectTitleMr();
        registerPage.setPassword("123456");

        registerPage.setDateOfBirth("10", "5", "1995");

        registerPage.subscribeNewsletter();
        registerPage.receiveOffers();

        registerPage.fillAddressInformation(
                "Teste",
                "Da Silva",
                "Empresa Teste",
                "Rua Teste, 123",
                "Apto 45",
                "Israel",
                "SP",
                "São Paulo",
                "01000-000",
                "11999999999"
        );

        AccountCreatedPage accountCreatedPage = registerPage.submitForm();

        assertThat("Tela de sucesso não está visível",
                accountCreatedPage.isVisible(), is(true));

        assertThat("Título incorreto",
                accountCreatedPage.getTitle(), equalTo("ACCOUNT CREATED!"));

        accountCreatedPage.clickContinue();
    }
}
