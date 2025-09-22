package org.example.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    private final WebDriver driver;

    private final By noButton = By.xpath("//button[text()='Нет']");
    private final By yesButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Да']");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage noButtonClick() {
        driver.findElement(noButton).click();
        return new OrderPage(driver);
    }

    public void yesButtonClick(){
        driver.findElement(yesButton).click();
    }

}
