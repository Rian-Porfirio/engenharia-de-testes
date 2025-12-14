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

/*
para campos de entrada de texto, criei um metodo preencherInput, que busca o elemento input que
precisa ser preenchido e insere o texto informado pelo desenvolvedor.

Utilizei em conjunto o metodo driver e um wait para garantir que o teste espere o elemento estar
disponivel para começar os trabalhos.
*/

/*
para os botões de radio e checkbox, criei um metodo unico que seleciona a primeira opção do conjunto de
possibilidades do input. Utilizei a mesma lógica de esperar o elemento estar disponivel na tela para que o
teste seja executado com sucesso.

O motivo de eu ter mantido apenas um metodo, e porque a logica de selecionar tanto checkbox quanto radio
e a mesma, utilizando o metodo do WebElement click() podendo ter ou não uma verificação se aquele elemento
que iremos clicar já está selecionado.

para o select (dropdown) utilizei uma classe de suporte do proprio selenium para a tag de select.
para utilizarmos ela, precisamos inicializar instanciar a classe com a referencia ao elemento select
que conseguimos adquirir utilizando um simples findElement do driver. Com isso, conseguimos ter acesso
a diversos métodos, incluindo o selectByVisibleText, que utilizamos para selecionar uma das opções do select
a partir do texto, facilitando o desenvolvedor a testar este componente.
 */
