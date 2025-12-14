package ecommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.BasePage;

import java.util.List;

public class ProductsList extends BasePage {
    private final By productsInventory = By.className("inventory_list");
    private final By productDiv = By.cssSelector(".inventory_item");

    public ProductsList(WebDriver driver) {
        super(driver);
    }

    public List<Product> listProducts() {
        WebElement productsList = find(productsInventory);
        return productsList.findElements(productDiv)
                .stream()
                .map(p -> new Product(driver, p))
                .toList();
    }

    public Product findProductByName(String productName) {
        return listProducts().stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .get();
    }
}
