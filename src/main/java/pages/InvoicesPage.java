package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class InvoicesPage extends BasePage {

    private By invoiceNumberField = By.cssSelector("td:nth-child(1)");
    private By invoiceTotalField = By.cssSelector("td:nth-child(4)");
    public String invoiceNumberFromList;
    public double invoiceTotal;


    public InvoicesPage(WebDriver driver) {
        super(driver);
    }

    public String getInvoiceNumberFromList() {
        clickOnElement(invoiceNumberField);
        invoiceNumberFromList = getElement(invoiceNumberField).getText().trim();
        return invoiceNumberFromList;
    }

    public double getInvoiceTotal() {
        clickOnElement(invoiceTotalField);
        WebElement element = getElement(invoiceTotalField);
        invoiceTotal = Double.parseDouble(element.getText().trim().substring(1));
        return invoiceTotal;
    }
}
