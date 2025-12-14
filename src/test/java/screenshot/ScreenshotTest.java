package screenshot;

import base.BaseSeleniumTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Teste realizando captura de tela inteira de um website")
     public void screenshot() throws IOException {
        driver.get("https://www.youtube.com/");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Para quem estiver testando, preste atencao na estrutura de arquivos do sistema. A pasta screenshot será criada.
        FileUtils.copyFile(screenshot, new File("screenshots/screenshot" + System.currentTimeMillis() + ".png"));
    }
}

