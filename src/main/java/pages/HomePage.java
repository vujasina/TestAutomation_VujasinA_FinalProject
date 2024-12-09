package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    private By signInLink = By.cssSelector("a[data-test='nav-sign-in']");
    private By categoriesDropDown = By.cssSelector("a[data-test='nav-categories']");
    private By powerToolsCategory = By.cssSelector("a[data-test='nav-power-tools']");
    private By pageButton = By.cssSelector(".page-link");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() {
        clickOnElement(signInLink);
    }

    public void openCategoriesDropDown() {
        clickOnElement(categoriesDropDown);
    }

    public void selectPowerToolsFromCategoriesDropDown() {
        clickOnElement(powerToolsCategory);
    }

    public void selectRandomPage() throws InterruptedException {
        int n = 0;
        int r = 0;

        List<WebElement> list = new ArrayList<>();
        list = findElements(pageButton);
        n = list.size();
        Random random = new Random();
        r = random.nextInt(n - 1);
        list.get(1 + r).click();

    }

}

