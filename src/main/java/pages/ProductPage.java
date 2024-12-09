package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class ProductPage extends BasePage {


    private By addToFavouritesButton = By.cssSelector("button[data-test='add-to-favorites'");
    private By productNameField = By.cssSelector("h1[data-test='product-name'");
    //    private By outOfStockField = By.cssSelector("p[data-test='out-of-stock'");
    private By addToCartButton = By.cssSelector("button[data-test='add-to-cart'");
    private By quantityField = By.cssSelector("input[data-test='quantity'");
    private By productTitleField = By.cssSelector("h1[data-test='product-name'");
    private By toastMessage = By.cssSelector("#toast-container");
    public String productName;


    public String nameOfProductAddedToFavorites;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String addProductToFavorites() {
        WebElement element = getElement(productNameField);
        nameOfProductAddedToFavorites = element.getText().trim();
        clickOnElement(addToFavouritesButton);
        return nameOfProductAddedToFavorites;
    }

//    public boolean productOutOfStock() throws NoSuchElementException {
//        try {
//            driver.findElement(outOfStockField);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }

    public void addProductToCart(int minimumQuantity, int maximumQuantity) {
        int r = 0;
        String q;

        Random random = new Random();
        r = minimumQuantity + random.nextInt(maximumQuantity - minimumQuantity);
        q = Integer.toString(r);
        clickOnElement(quantityField);
        driver.findElement(quantityField).clear();
        typeIn(quantityField, q);
        clickOnElement(addToCartButton);
    }

    public void clickOnToastMessage() {
        clickOnElement(toastMessage);
    }

    public String getProductName() {
        WebElement element = getElement(productTitleField);
        productName = element.getText().trim();
        return productName;
    }

}

