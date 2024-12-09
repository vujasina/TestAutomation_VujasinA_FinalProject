package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class BasketTest extends BaseTest {

    RegisterPage registerPage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    HomePage homePage;
    ProductPage productPage;
    BasketPage basketPage;
    InvoicesPage invoicesPage;

    @BeforeMethod
    public void basketSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
        invoicesPage = new InvoicesPage(driver);

    }

    @Test(description = "Complete purchase flow for at least 2 items of two products each; " +
            "Expected result: Sum of prices added into basket is correct and purchase is successfully completed")
    public void basketTest() throws InterruptedException {
        homePage.goToLoginPage();
        loginPage.goToRegisterPage();
        registerPage.registerUser();
        loginPage.loginUser(registerPage.getUsername(), registerPage.getPassword());
        productPage.goToFavoritesPage();
        for (int i = 0; i < 2; ) {
            myAccountPage.goToHomePage();
            homePage.selectRandomPage();
            homePage.selectRandomProductOnPage();
            productPage.getProductName();
            if (!(productPage.productName.equals("Long Nose Pliers"))) {
                productPage.addProductToCart(2, 6);
                productPage.clickOnToastMessage();
                productPage.goToBasketPage();
                i++;
            } else {

            }
        }
        boolean verification1 = basketPage.verifyNumberInRedBaloonIsOk();
        boolean verification2 = basketPage.verifyTotalAmountToBePayed();
        boolean verification3 = basketPage.verifyCheckOutFlow();
        boolean verification4 = invoicesPage.getInvoiceNumberFromList().equals(basketPage.getInvoiceNumberFromMessage());
        boolean verification5 = invoicesPage.getInvoiceTotal() == basketPage.getTotalAmountToBePayed();
        System.out.println("Verification1: " + verification1);
        System.out.println("Verification2: " + verification2);
        System.out.println("Verification3: " + verification3);
        System.out.println("Verification4: " + verification4);
        System.out.println("Verification5: " + verification5);
        if (verification1 & verification2 & verification3 & verification4 & verification5) {
            Assert.assertTrue(true, "Purchase was not successful");
        } else
            Assert.assertTrue(false, "Purchase was not successful");
    }

}

