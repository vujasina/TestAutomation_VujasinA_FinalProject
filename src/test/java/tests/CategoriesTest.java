package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CategoriesTest extends BaseTest {

    HomePage homePage;
    PowerToolsPage powerToolsPage;
    private int numberOfProductCardsDisplayedBeforeFilter;
    private int numberOfProductCardsDisplayedAfterFilter;

    @BeforeMethod
    public void categoriesSetup() {
        homePage = new HomePage(driver);
        powerToolsPage = new PowerToolsPage(driver);
    }


    @Test(description = "Power Tools/Drill category filter test; Expected result: User successfully filters tools by Power Tools/Drill category")
    public void powerToolsDrillCategoryTest() throws InterruptedException {
        homePage.openCategoriesDropDown();
        homePage.selectPowerToolsFromCategoriesDropDown();
        numberOfProductCardsDisplayedBeforeFilter = powerToolsPage.countProductCardsDisplayed();
        powerToolsPage.selectDrillSubcategory();
        numberOfProductCardsDisplayedAfterFilter = powerToolsPage.countProductCardsDisplayed();
        System.out.println("Number of product cards displayed before filter: " + numberOfProductCardsDisplayedBeforeFilter);
        System.out.println("Number of product cards displayed after filter: " + numberOfProductCardsDisplayedAfterFilter);
        Assert.assertTrue(numberOfProductCardsDisplayedAfterFilter < numberOfProductCardsDisplayedBeforeFilter, "Power Tools/Drill category filter is not applied");
    }

}
