package formulario;

import base.BaseSeleniumTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import page.FormPage;

public class ManipulacaoFormularioTeste extends BaseSeleniumTest {

    private final By primeiroNomeInput = By.id("first-name");
    private final By ultimoNomeInput = By.id("last-name");
    private final By cargoInput = By.id("job-title");
    private final By highSchoolRadio = By.id("radio-button-1");
    private final By maleCheckbox = By.id("checkbox-1");
    private final By selectMenu = By.id("select-menu");
    private final By datePicker = By.id("datepicker");
    private final By submitButton = By.linkText("Submit");

    @Test
    @DisplayName("Teste populando informações do formy")
    public void formulario() {
        new FormPage(driver).open("https://formy-project.herokuapp.com/form")
                .preencherInput(primeiroNomeInput, "usuario")
                .preencherInput(ultimoNomeInput, "ultimo nome")
                .preencherInput(cargoInput, "desenvolvedor")
                .setOption(highSchoolRadio)
                .setOption(maleCheckbox)
                .select(selectMenu, "2-4")
                .preencherInput(datePicker, "21/10/2025")
                .submit(submitButton);
    }

}
