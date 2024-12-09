package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    private By myAccountPageTitle = By.cssSelector("h1[data-test='page-title']");
    private By profileText = By.cssSelector("a[data-test='nav-profile']");
    private By favoritesLink = By.cssSelector("a[data-test='nav-favorites'");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserOnMyAccountPage() {
        return matchesExpectedText(myAccountPageTitle, "My account") &&
                matchesExpectedText(profileText, "Profile");
    }

}
