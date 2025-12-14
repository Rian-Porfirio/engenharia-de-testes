package Ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.BasePage;

public class CartItem extends BasePage {
    private final By name = By.className("inventory_item_name");
    private final By price = By.className("inventory_item_price");
    private final By quantity = By.className("cart_quantity");

    public CartItem(WebDriver driver) {
        super(driver);
    }

    public String productName() {
        return find(name).getText();
    }

    public String productPrice() {
        return find(price).getText();
    }

    public String productQuantity() {
        return find(quantity).getText();
    }
}
