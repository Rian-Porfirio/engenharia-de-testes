package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.BasePage;

public class Product extends BasePage {
    private final By name = By.className("inventory_item_name");
    private final By price = By.className("inventory_item_price");

    private final WebElement product;

    public Product(WebDriver driver, WebElement product) {
        super(driver);
        this.product = product;
    }

    public void addToCart() {
        product.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
    }

    public String getName() {
        return product.findElement(name).getText();
    }

    public String getPrice() {
        return product.findElement(price).getText();
    }
}
