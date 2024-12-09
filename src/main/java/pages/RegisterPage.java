package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Locale;

public class RegisterPage extends BasePage {

    private By firstNameFiled = By.id("first_name");
    private By lastNameFiled = By.id("last_name");
    private By dobFiled = By.id("dob");
    private By addressFiled = By.id("address");
    private By postCodeFiled = By.id("postcode");
    private By cityFiled = By.id("city");
    private By stateFiled = By.id("state");
    private By countryFiled = By.id("country");
    private By phoneFiled = By.id("phone");
    private By emailFiled = By.id("email");
    private By passwordFiled = By.cssSelector("input[data-test='password']");
    private By registerButton = By.cssSelector(".btnSubmit");

    Faker faker = new Faker(new Locale("en-US"));

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private String username;
    private String password;
    private String firstName;
    private String lastName;


    public void registerUser() {
        username = faker.internet().emailAddress();
        password = (faker.internet().password(12, 14, true, true, true) + "aA!3");
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        typeIn(firstNameFiled, firstName);
        typeIn(lastNameFiled, lastName);
        enterDateOfBirth();
        typeIn(addressFiled, faker.address().fullAddress());
        typeIn(postCodeFiled, faker.number().digits(6));
        typeIn(cityFiled, faker.address().city());
        typeIn(stateFiled, faker.address().state());
        selectCountry();
        typeIn(phoneFiled, faker.number().digits(10));
        typeIn(emailFiled, username);
        typeIn(passwordFiled, password);
        clickOnElement(registerButton);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void enterDateOfBirth() {
        typeIn(dobFiled, "1212");
        new Actions(driver)
                .sendKeys(Keys.TAB)
                .perform();
        typeIn(dobFiled, "2000");

    }

    private void selectCountry() {
        WebElement element = getElement(countryFiled);
        Select select = new Select(element);
        select.selectByValue("US");
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}

