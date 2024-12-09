package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FavoritesPage extends BasePage {

    private By productNameField = By.cssSelector("h5[data-test='product-name'");
    public String nameOfProductPresentOnFavoritesPage;


    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public String getNameOfProductPresentOnFavoritesPage() {
        WebElement element = getElement(productNameField);
        nameOfProductPresentOnFavoritesPage = element.getText().trim();
        return nameOfProductPresentOnFavoritesPage;
    }
}
