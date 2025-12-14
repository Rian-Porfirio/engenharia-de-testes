package rolagem;

import base.BaseSeleniumTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RolagemPaginaTeste extends BaseSeleniumTest {

    private final By link = By.linkText("Practice Test Automation.");

    @Test
    @DisplayName("Teste para iniciar uma rolagem e então selecionar um botão que não estava visível")
    public void rolagem() {

        driver.get("https://practicetestautomation.com/courses/");

        // Aqui eu apenas localizo o elemento no DOM
        WebElement linkElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(link)
        );

        // É exatamente nesse momento em que eu faço o scroll da página até o elemento que eu especifiquei utilizando o scrollIntoView
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", linkElement);

        // Nessa etapa eu espero o elemento aparecer na tela e ficar clicável
        WebElement a = wait.until(ExpectedConditions.elementToBeClickable(linkElement));
        Assertions.assertNotNull(a);

        a.click();
    }
}
