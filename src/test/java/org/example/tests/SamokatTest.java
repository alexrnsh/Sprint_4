package org.example.tests;


import org.junit.Assert;
import org.junit.Test;
import org.example.helpers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@RunWith(Parameterized.class)
public class SamokatTest extends BaseTest {

    private String firstName;
    private String lastName;
    private String address;
    private String station;
    private String phone;
    private String date;
    private String rentalPeriod;

    public SamokatTest(String firstName, String lastName, String address,
                                      String station, String phone, String date, String rentPeriod) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentPeriod;
    }


    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Александра", "Реньш", "Кузнецовская, 21", "Сокольники", "89119991111", "20.09.2025", "двое суток"},
                {"Максим", "Максимов", "Садовая, 100", "Тверская", "89223334455", "25.09.2025", "трое суток"}
        };
    }

    @Test
    public void checkRegistrationFormUpperOrderButton() {

        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        By confirmationSuccess = By.cssSelector("div.Order_ModalHeader__3FDaJ");

        objHomePage.waitUntilUpperOrderButtonVisible();

        Assert.assertTrue("Верхняя кнопка 'Заказать' не отображается",objHomePage.isUpperOrderButtonDisplayed());

        LoginToOrder objLoginPage = objHomePage.upperOrderButtonPress();
        objLoginPage.fillFormToLogin(this.firstName, this.lastName,
                                    this.address, this.station, this.phone);

        OrderPage objOrderPage = objLoginPage.clickNextButton();
        objOrderPage.fillSecondPage(date, rentalPeriod);

        ConfirmationPage objConfirmationPage = objOrderPage.clickOrderButton();
        objConfirmationPage.yesButtonClick();
        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationSuccess));
        Assert.assertTrue("Окно подтверждения заказа не отображается", header.isDisplayed() && header.getText().contains("Заказ оформлен"));



    }

    @Test

    public void checkRegistrationFormLowerOrderButton() {
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        By confirmationSuccess = By.cssSelector("div.Order_ModalHeader__3FDaJ");

        objHomePage.waitUntilLowerOrderButtonVisible();

        Assert.assertTrue("Нижняя кнопка 'Заказать' не отображается", objHomePage.isLowerOrderButtonDisplayed());

        LoginToOrder objLoginPage = objHomePage.lowerOrderButtonPress();
        objLoginPage.fillFormToLogin(this.firstName, this.lastName,
                this.address, this.station, this.phone);

        OrderPage objOrderPage = objLoginPage.clickNextButton();
        objOrderPage.fillSecondPage(date, rentalPeriod);

        ConfirmationPage objConfirmationPage = objOrderPage.clickOrderButton();
        objConfirmationPage.yesButtonClick();
        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationSuccess));
        Assert.assertTrue("Окно подтверждения заказа не отображается", header.isDisplayed() && header.getText().contains("Заказ оформлен"));
    }


}
