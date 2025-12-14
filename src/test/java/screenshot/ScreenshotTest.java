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

/*
Um excelente cenário para prints de tela durante testes, é assim que um teste falhar não propositalmente, o sistema tirar uma print para mostrar
o exato momento em que o erro aconteceu e registrar essa print em alguma pasta de evidencias. Dessa maneira, ficará muito mais fácil
de identificar qual teria sido problema através de uma imagem do EXATO MOMENTO em que o erro aconteceu, evitando que o desenvolvedor fique tentando
desvendar como aquele bug aconteceu.

para deixar estas imagens salvas, podemos fazer como no exemplo do meu código, onde fazemos uso da classe utilitária FileUtils, que nos permite
transferir essa imagem para algum arquivo do nosso computador, incluindo dentro do nosso sistema. No meu código, eu faço a cópia da screenshot
para uma pasta chamada screenshots que deve ficar localizada fora do SRC do sistema. Caso essa pasta não exista, ela é criada automaticamente.
além disso é interessante salvar quando essa print aconteceu com um currentTimeMillis() tanto para diferenciar uma imagem da outra, quanto para
também identificar quando aquele evento aconteceu.
 */
