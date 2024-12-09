package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PowerToolsPage extends BasePage {

    private By drillCheckBox = By.cssSelector("#filters > div:nth-child(7) > ul > div:nth-child(4) > label");

    public PowerToolsPage(WebDriver driver) {
        super(driver);
    }

    public void selectDrillSubcategory() {
        clickOnElement(drillCheckBox);
    }


}
