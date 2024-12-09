package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super(driver);

    }

    private By numberOfItems = By.cssSelector("input[data-test='product-quantity'");
    private By pricePerItem = By.cssSelector("span[data-test='product-price'");
    private By totalField = By.cssSelector("td[data-test='cart-total'");
    private By proceedToCheckoutButton1 = By.cssSelector("button[data-test='proceed-1'");
    private By proceedToCheckoutButton2 = By.cssSelector("button[data-test='proceed-2'");
    private By proceedToCheckoutButton3 = By.cssSelector("button[data-test='proceed-3'");
    private By paymentMethodDropDown = By.cssSelector("select[data-test='payment-method'");
    private By confirmButton = By.cssSelector("button[data-test='finish'");
    private By paymentSuccessfulMessageField = By.cssSelector(".help-block");
    private By orderConfirmedTextField = By.xpath("//*[@id=\"order-confirmation\"]");
    private By invoiceNumberInMessage = By.cssSelector("#order-confirmation > span");
    private String paymentSuccessfulMessage;
    public String orderConfirmedText;
    public String invoiceNumberFromMessage;
    public String orderConfirmedMessageText;
    public double totalAmountToBePayed;


    public boolean verifyNumberInRedBaloonIsOk() throws InterruptedException {
        List<WebElement> list = new ArrayList<>();
        list = findElements(numberOfItems);
        int n = 0;
        int m = 0;
        for (int i = 0; i < list.size(); i++) {
            n = n + Integer.parseInt(list.get(i).getAttribute("value"));
//            System.out.println(list.get(i).getAttribute("value"));
        }
        m = Integer.parseInt(getElement(redBaloon).getText().trim());
//        System.out.println(getElement(redBaloon).getText().trim());
        if (n == m) {
            return true;
        } else
            return false;
    }

    public boolean verifyTotalAmountToBePayed() throws InterruptedException {
        List<WebElement> list1 = new ArrayList<>();
        List<WebElement> list2 = new ArrayList<>();
        WebElement totalElement;
        list1 = findElements(numberOfItems);
        list2 = findElements(pricePerItem);
        totalElement = getElement(totalField);

        double total = 0.0;
        double valueInTotalField = 0.0;
        for (int i = 0; i < list1.size(); i++) {
            total = total + (Integer.parseInt(list1.get(i).getAttribute("value")) * Double.parseDouble(list2.get(i).getText().trim().substring(1)));

        }
        BigDecimal totalBD = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        valueInTotalField = Double.parseDouble(totalElement.getText().trim().substring(1));
        BigDecimal valueInTotalFieldBD = new BigDecimal(valueInTotalField).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Total calculated = " + totalBD);
        System.out.println("Value in the Total field = " + valueInTotalFieldBD);
        totalAmountToBePayed = valueInTotalField;
        if (totalBD.equals(valueInTotalFieldBD)) {
            return true;
        } else {
            return false;
        }

    }

    private void selectPaymentMethod() {
        WebElement element = getElement(paymentMethodDropDown);
        Select select = new Select(element);
        select.selectByValue("cash-on-delivery");
    }

    public String getInvoiceNumberFromMessage() {

        return invoiceNumberFromMessage;
    }

    public String getOrderConfirmedMessageText() {

        return orderConfirmedText;
    }

    public String getPaymentSuccessfulMessage() {

        return paymentSuccessfulMessage;
    }

    public double getTotalAmountToBePayed() {
        return totalAmountToBePayed;
    }

    public boolean verifyCheckOutFlow() {
        clickOnElement(proceedToCheckoutButton1);
        clickOnElement(proceedToCheckoutButton2);
        clickOnElement(proceedToCheckoutButton3);
        selectPaymentMethod();
        clickOnElement(confirmButton);
        paymentSuccessfulMessage = getElement(paymentSuccessfulMessageField).getText().trim();
        System.out.println("Payment successful message: " + paymentSuccessfulMessage);
        boolean V1 = getPaymentSuccessfulMessage().equals("Payment was successful");
        clickOnElement(confirmButton);
        orderConfirmedMessageText = getElement(orderConfirmedTextField).getAttribute("textContent");
        orderConfirmedText = orderConfirmedMessageText.substring(0, 45);
        System.out.println("Order confirmation text: " + orderConfirmedText);
        boolean V2 = getOrderConfirmedMessageText().equals("Thanks for your order! Your invoice number is");
        invoiceNumberFromMessage = getElement(invoiceNumberInMessage).getText().trim();
//        String invoiceNumberFromMessage = orderConfirmedMessage.substring(45, orderConfirmedMessage.length() - 1);
        System.out.println("Invoice number from message: " + invoiceNumberFromMessage);
        goToInvoicesPage();
        System.out.println("V1: " + V1 + ", V2: " + V2);
        if (V1 & V2) {
            System.out.println("Check out flow verification: Passed");
            return true;
        } else {
            System.out.println("Check out flow verification: Failed");
            return false;
        }
    }

}

