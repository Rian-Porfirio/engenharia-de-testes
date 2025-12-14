package Ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.BasePage;

public class Cart extends BasePage {
    private final By checkout = By.id("checkout");

    public Cart(WebDriver driver) {
        super(driver);
    }

    public CartItem getCartItem() {
        return new CartItem(driver);
    }

    public void checkout() {
        click(checkout);
    }
}
