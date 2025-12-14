package register;

import base.BaseSeleniumTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import page.FormPage;

public class UserRegisterTest extends BaseSeleniumTest {

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");

    private final By radio = By.xpath("//label[@for='gender-radio-1']");
    private final By phoneNumber = By.id("userNumber");
    private final By hobbie = By.xpath("//label[@for='hobbies-checkbox-2']");

    private final By address = By.id("currentAddress");
    private final By submit = By.id("submit");

    @Test
    @DisplayName("Testa registar um novo usuário no sistema")
    public void register() {
        driver.get("https://demoqa.com/automation-practice-form/");

        new FormPage(driver)
                .preencherInput(firstName, "Teste primeiro nome")
                .preencherInput(lastName, "Teste ultimo nome")
                .preencherInput(email, "teste@gmail.com")
                .setOption(radio)
                .preencherInput(phoneNumber, "1728238329")
                .setOption(hobbie)
                .preencherInput(address, "Teste endereço")
                .submit(submit);
    }
}
