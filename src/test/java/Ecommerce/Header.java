package Ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.BasePage;

public class Header extends BasePage {
    private final By cartButton = By.className("shopping_cart_link");

    public Header(WebDriver driver) {
        super(driver);
    }

    public void goToCart() {
        click(cartButton);
    }
}
