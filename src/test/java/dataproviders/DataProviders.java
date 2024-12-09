package dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider(){
        return new Object[][]{
                {"" , "password", "1"},
                {"" , "" , "1+2"},
                {"username@somesite.com" , "", "2"},
                {"username" , "pa", "3+4"},
                {"username@somesite.com" , "password", "5"}
        };
    }
}
