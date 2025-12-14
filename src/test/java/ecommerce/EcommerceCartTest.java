package ecommerce;

import base.BaseSeleniumTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import page.FormPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class EcommerceCartTest extends BaseSeleniumTest {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");

    private final By headerPaginaFinal = By.className("complete-header");

    @Test
    @DisplayName("Testa adicionar um item ao carrinho do ecommerce")
    public void cart() {
        FormPage form = new FormPage(driver)
                .open("https://www.saucedemo.com/")
                .preencherInput(usernameInput, "standard_user")
                .preencherInput(passwordInput, "secret_sauce")
                .submit(loginButton);

        assertThat(driver.getCurrentUrl(), containsString("/inventory.html"));

        ProductsList productsList = new ProductsList(driver);
        Product product = productsList.findProductByName("Sauce Labs Backpack");
        assertThat(product.getName(), containsString("Sauce Labs Backpack"));
        assertThat(product.getPrice(), containsString("$29.99"));
        product.addToCart();

        new Header(driver).goToCart();

        Cart cart = new Cart(driver);
        CartItem cartItem = cart.getCartItem();
        assertThat(cartItem.productName(), containsString("Sauce Labs Backpack"));
        assertThat(cartItem.productPrice(), containsString("$29.99"));
        assertThat(cartItem.productQuantity(), containsString("1"));
        cart.checkout();

        form.preencherInput(firstName, "primeiro nome teste")
                .preencherInput(lastName, "ultimo nome teste")
                .preencherInput(postalCode, "13670-028")
                .submit(continueButton)
                .submit(finishButton);
       assertThat(driver.getCurrentUrl(), containsString("/checkout-complete.html"));

       String sucessMessage = driver.findElement(headerPaginaFinal).getText();
       assertThat(sucessMessage, containsString("Thank you for your order!"));
    }

}
/*
A interface que utilizei para testes não nos permite alterar a quantidade do produto, mas seguindo uma regra
comum de E-commerce, temos duas opções comuns no primeiro momento para selecionar a quantidade do produto, um select ou clicar diversas vezes no botão de "Adicionar ao carrinho"

A lógica caminho "Feliz": clicou X vezes no botao de adicionar, você terá o mesmo X de produtos no carrinho

então em uma interface que te dá essas duas possibilidades que citei, a lógica seria simular uma certa quantidade de cliques e também
testar interagir com um select utilizando a classe de suporte do selenium.
-----

Caso haja a necessidade de realizar uma rolagem na pagina para que o selenium consiga interagir com a interface, seria uma boa opção
utilizar um WebDriverWait em conjunto com um script javascript para que, quando rodarmos o script de rolagem da página, somente iremos selecionar
o elemento que estamos buscando, quando ele ficar realmente disponivel na nossa tela

um exemplo de código seria:
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); <- enquanto fazia a atividade, percebi que 10 segundos é pouco dependendo do site, então seria bom aumentar um pouco esse tempo... (isso na questão de carregamento da pagina)

WebElement linkElement = wait.until(
        ExpectedConditions.presenceOfElementLocated(By.linkText("Selenium WebDriver"))
);

JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].scrollIntoView(true);", linkElement);

WebElement a = wait.until(ExpectedConditions.elementToBeClickable(linkElement));
Assertions.assertNotNull(a);

a.click();
 */
