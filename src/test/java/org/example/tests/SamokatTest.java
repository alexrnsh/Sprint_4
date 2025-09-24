package org.example.tests;


import org.junit.Assert;
import org.junit.Test;
import org.example.pages.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@RunWith(Parameterized.class)
public class SamokatTest extends BaseTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final OrderButtonType buttonType;
    private final By confirmationSuccess = By.cssSelector("div.Order_ModalHeader__3FDaJ");

    public SamokatTest(String firstName, String lastName, String address,
                                      String station, String phone, String date, String rentPeriod, OrderButtonType buttonType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentPeriod;
        this.buttonType = buttonType;
    }


    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Александра", "Реньш", "Кузнецовская, 21", "Сокольники", "89119991111", "20.09.2025", "двое суток", OrderButtonType.UPPER},
                {"Максим", "Максимов", "Садовая, 100", "Тверская", "89223334455", "25.09.2025", "трое суток", OrderButtonType.LOWER}
        };
    }

    @Test

    public void checkRegistrationFormUpperOrderButton() {

        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        // By confirmationSuccess = By.cssSelector("div.Order_ModalHeader__3FDaJ");
        FormFirstPage objFirstPage;
        if (OrderButtonType.UPPER.equals(this.buttonType)) {

            objHomePage.waitUntilUpperOrderButtonVisible();

            Assert.assertTrue("Верхняя кнопка 'Заказать' не отображается", objHomePage.isUpperOrderButtonDisplayed());

            objFirstPage = objHomePage.upperOrderButtonPress();
        }

        else {
            objHomePage.waitUntilLowerOrderButtonVisible();

            Assert.assertTrue("Нижняя кнопка 'Заказать' не отображается", objHomePage.isLowerOrderButtonDisplayed());

            objFirstPage = objHomePage.lowerOrderButtonPress();

        }

        objFirstPage.fillFormToLogin(this.firstName, this.lastName,
                                    this.address, this.station, this.phone);

        FormSecondPage objFormSecondPage = objFirstPage.clickNextButton();
        objFormSecondPage.fillSecondPage(date, rentalPeriod);

        ConfirmationPage objConfirmationPage = objFormSecondPage.clickOrderButton();
        objConfirmationPage.yesButtonClick();
        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationSuccess));
        Assert.assertTrue("Окно подтверждения заказа не отображается", header.isDisplayed() && header.getText().contains("Заказ оформлен"));



    }
    public enum OrderButtonType {
        UPPER, LOWER
    }

}
