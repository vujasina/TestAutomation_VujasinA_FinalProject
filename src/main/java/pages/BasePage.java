package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;
    protected By productCard = By.cssSelector("a[class='card']");
    protected By homePageLink = By.cssSelector("a[data-test='nav-home']");
    protected By userDropDown = By.cssSelector("a[data-test='nav-menu'");
    protected By myFavoritesLink = By.cssSelector("a[data-test='nav-my-favorites'");
    protected By myInvoicesLink = By.cssSelector("a[data-test='nav-my-invoices'");
    protected By basket = By.cssSelector("svg[data-icon='cart-shopping'");
    protected By redBaloon = By.cssSelector("span[data-test='cart-quantity'");
    protected By signOutLink = By.cssSelector("a[data-test='nav-sign-out'");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findElements(By locator) throws InterruptedException {
        Thread.sleep(500);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

    }

    protected void typeIn(By locator, String text) {
        WebElement element = getElement(locator);
        element.sendKeys(text);
    }

    protected void clickOnElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException cl) {
            cl.printStackTrace();
            wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
        } catch (TimeoutException t) {
            getElement(locator).click();
        } catch (Exception e) {
            WebElement element = getElement(locator);
            js.executeScript("arguments[0].click();", element);
        }

    }

    protected boolean matchesExpectedText(By locator, String expectedText) {
        WebElement element = getElement(locator);
        if (element.getText().trim().equals(expectedText)) {
            return true;
        } else {
            return false;
        }
    }

    public int countProductCardsDisplayed() throws InterruptedException {
        int n = 0;
        List<WebElement> list = new ArrayList<>();
        list = findElements(productCard);
        n = list.size();
        return n;
    }

    public void goToHomePage() {
        clickOnElement(homePageLink);
    }

    public void selectRandomProductOnPage() throws InterruptedException {
        int n = 0;
        int r = 0;

        List<WebElement> list = new ArrayList<>();
        list = findElements(productCard);
        n = list.size();
        Random random = new Random();
        r = random.nextInt(n - 1);
        list.get(r).click();

    }

    public void goToFavoritesPage() {
        clickOnElement(userDropDown);
        clickOnElement(myFavoritesLink);
    }

    public void goToInvoicesPage() {
        clickOnElement(userDropDown);
        clickOnElement(myInvoicesLink);
    }

    public void goToBasketPage() {
        clickOnElement(basket);
    }

//    public void signOut(){
//        clickOnElement(userDropDown);
//        clickOnElement(signOutLink);
//    }

//    public int numberInRedBaloon (){
//        int n = 0;
//        String s;
//        WebElement element = getElement(redBaloon);
//        s = element.getText().trim();
//        n = Integer.parseInt(s);
//        return n;
//    }

}
