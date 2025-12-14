package Ecommerce;

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
