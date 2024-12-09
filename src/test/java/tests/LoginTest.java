package tests;

import dataproviders.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class LoginTest extends BaseTest {

    RegisterPage registerPage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    HomePage homePage;
    ProductPage productPage;


    @BeforeMethod
    public void loginSetup() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }


    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class, description = "Login user negative test; Expected result: user is presented with correct error messages when invalid credentials are provided")
    public void loginUserNegativeTest(String username, String password, String assertCondition) {
        homePage.goToLoginPage();
        loginPage.loginUser(username, password);
        if (assertCondition.equals("1")) {
            Assert.assertTrue(loginPage.isEmailErrorMessageEmailRequired());
        } else if (assertCondition.equals("2")) {
            Assert.assertTrue(loginPage.isPasswordErrorMessagePasswordRequired());
        } else if (assertCondition.equals("1+2")) {
            Assert.assertTrue(loginPage.isEmailErrorMessageEmailRequired() && loginPage.isPasswordErrorMessagePasswordRequired());
        } else if (assertCondition.equals("3+4")) {
            Assert.assertTrue(loginPage.isEmailErrorMessageFormatInvalid() && loginPage.isPasswordErrorMessagePasswordLengthInvalid());
        } else if
        (assertCondition.equals("5")) {
            Assert.assertTrue(loginPage.isInvalidCredentialsMessageCorrect());
        }
    }
}
