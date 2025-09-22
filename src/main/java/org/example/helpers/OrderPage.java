package org.example.helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage {
    private final WebDriver driver;

    // локаторы для обязательных полей страницы заказа (2 страница)
    private final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.cssSelector("div.Dropdown-control");
    private final By rentalOptions = By.cssSelector("div.Dropdown-menu .Dropdown-option");
    private final By orderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    private final By backButton = By.xpath("//button[text()='Назад']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillSecondPage(String date, String period){

        driver.findElement(deliveryDateInput).click();
        driver.findElement(deliveryDateInput).sendKeys(date);
        driver.findElement(deliveryDateInput).sendKeys(Keys.ENTER);

        driver.findElement(rentalPeriodDropdown).click();

        List<WebElement> options = driver.findElements(rentalOptions);


        for (WebElement option : options) {
            if (option.getText().equals(period)) {
                option.click();
                break;
            }
        }
    }

    public ConfirmationPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return new ConfirmationPage(driver);
    }

    public LoginToOrder clickBackButton() {
        driver.findElement(backButton).click();
        return new LoginToOrder(driver); // возврат на предыдущую страницу
    }
}

