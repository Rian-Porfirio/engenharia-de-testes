package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormPage extends BasePage {
    public FormPage(WebDriver driver) {
        super(driver);
    }

    public FormPage open(String url) {
        driver.get(url);
        return this;
    }

    public FormPage preencherInput(By locator, String text) {
        type(locator, text);
        return this;
    }

    public FormPage setOption(By locator) {
        WebElement option = find(locator);

        if (!option.isSelected()) {
            option.click();
        }

        return this;
    }

    public FormPage select(By locator, String option) {
        WebElement dropdown = find(locator);

        Select select = new Select(dropdown);
        select.selectByVisibleText(option);

        return this;
    }

    public FormPage submit(By locator) {
        click(locator);
        return this;
    }
}
