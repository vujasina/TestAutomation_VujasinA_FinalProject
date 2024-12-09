package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailFiled = By.id("email");
    private By passwordFiled = By.cssSelector("#password");
    private By loginButton = By.cssSelector(".btnSubmit");
    private By registerPageLink = By.cssSelector("a[data-test='register-link']");
    private By emailErrorMessage = By.cssSelector("#email-error > div");
    private By passwordErrorMessage = By.cssSelector("#password-error > div");
    private By invalidCredentialsMessage = By.cssSelector(".help-block");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginUser(String username, String password) {
        typeIn(emailFiled, username);
        typeIn(passwordFiled, password);
        clickOnElement(loginButton);
    }

    public void goToRegisterPage() {
        clickOnElement(registerPageLink);
    }

    public boolean isUserOnLoginPage() {
        return matchesExpectedText(loginButton, "Login");
    }

    public boolean isEmailErrorMessageEmailRequired() {
        // Assert Condition "1"
        return matchesExpectedText(emailErrorMessage, "Email is required");
    }

    public boolean isPasswordErrorMessagePasswordRequired() {
        // Assert Condition "2"
        return matchesExpectedText(passwordErrorMessage, "Password is required");
    }

    public boolean isEmailErrorMessageFormatInvalid() {
        // Assert Condition "3"
        return matchesExpectedText(emailErrorMessage, "Email format is invalid");
    }

    public boolean isPasswordErrorMessagePasswordLengthInvalid() {
        // Assert Condition "4"
        return matchesExpectedText(passwordErrorMessage, "Password length is invalid");
    }

    public boolean isInvalidCredentialsMessageCorrect() {
        // Assert Condition "5"
        return matchesExpectedText(invalidCredentialsMessage, "Invalid email or password");
    }


}
