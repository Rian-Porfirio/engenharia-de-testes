package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage{

    private final By accountCreatedTitle =
            By.cssSelector("h2[data-qa='account-created']");

    private final By successMessage =
            By.xpath("//p[contains(text(),'Congratulations')]");

    private final By continueButton =
            By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(accountCreatedTitle);
    }

    public boolean isVisible() {
        return driver.findElement(accountCreatedTitle).isDisplayed();
    }

    public void clickContinue() {
        click(continueButton);
    }
}
