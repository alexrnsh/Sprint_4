package org.example.helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginToOrder {
    private WebDriver driver;

    // локаторы для полей формы заказа (1 страница)
    private By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");
    private By metroStationOptions = By.cssSelector(".select-search__option");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");

    public LoginToOrder(WebDriver driver){
        this.driver = driver;
    }

    public OrderPage clickNextButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(nextButton));

        WebElement element = driver.findElement(nextButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(nextButton).click();
        return new OrderPage(driver);
    }

    public void fillFormToLogin(String firstName, String lastName, String address, String stationName, String phoneNumber){
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroStationInput).click();
        driver.findElement(metroStationInput).sendKeys(stationName);

        List<WebElement> options = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(metroStationOptions));

        // Ищем нужный вариант по тексту
        for (WebElement option : options) {
            if (option.getText().equals(stationName)) {
                option.click();
                break;
            }
        }
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phoneNumber);

    }

}
