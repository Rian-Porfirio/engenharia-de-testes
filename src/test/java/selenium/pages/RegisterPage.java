package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {

    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");

    private final By password = By.id("password");

    private final By days = By.id("days");
    private final By months = By.id("months");
    private final By years = By.id("years");

    private final By newsletter = By.id("newsletter");
    private final By optin = By.id("optin");

    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");

    private final By createAccountButton =
            By.cssSelector("button[data-qa='create-account']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void selectTitleMr() {
        click(titleMr);
    }

    public void selectTitleMrs() {
        click(titleMrs);
    }

    public void setPassword(String value) {
        type(password, value);
    }

    public void setDateOfBirth(String day, String month, String year) {
        new Select(driver.findElement(days)).selectByValue(day);
        new Select(driver.findElement(months)).selectByValue(month);
        new Select(driver.findElement(years)).selectByValue(year);
    }

    public void subscribeNewsletter() {
        click(newsletter);
    }

    public void receiveOffers() {
        click(optin);
    }

    public void fillAddressInformation(
            String firstNameValue,
            String lastNameValue,
            String companyValue,
            String address1Value,
            String address2Value,
            String countryValue,
            String stateValue,
            String cityValue,
            String zipcodeValue,
            String mobileValue
    ) {
        type(firstName, firstNameValue);
        type(lastName, lastNameValue);
        type(company, companyValue);
        type(address1, address1Value);
        type(address2, address2Value);

        new Select(driver.findElement(country)).selectByVisibleText(countryValue);

        type(state, stateValue);
        type(city, cityValue);
        type(zipcode, zipcodeValue);
        type(mobileNumber, mobileValue);
    }

    public AccountCreatedPage submitForm() {
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }
}
