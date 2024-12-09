package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class WishListTest extends BaseTest {

    RegisterPage registerPage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    HomePage homePage;
    ProductPage productPage;
    FavoritesPage favoritesPage;

    @BeforeMethod
    public void wishListSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        favoritesPage = new FavoritesPage(driver);
    }


    @Test(description = "Add product to Wish List; Expected result: product is successfully added to Wish List")
    public void addProductToWishListTest() throws InterruptedException {
        homePage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
        productPage.goToFavoritesPage();
        myAccountPage.goToHomePage();
        homePage.selectRandomProductOnPage();
        productPage.addProductToFavorites();
        productPage.goToFavoritesPage();
        favoritesPage.getNameOfProductPresentOnFavoritesPage();
        Assert.assertTrue(productPage.nameOfProductAddedToFavorites.equals(favoritesPage.getNameOfProductPresentOnFavoritesPage()),
                "Product was not successfully added to Favorites");
    }


}
