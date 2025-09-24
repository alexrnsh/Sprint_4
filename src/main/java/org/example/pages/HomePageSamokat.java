package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageSamokat {
    private final WebDriver driver;


    // локатор верхней и нижней кнопок "Заказать"
    private final By upperOrderButton= By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']");
    private final By lowerOrderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    //локатор кнопки принятия куки
    private final By cookieButton = By.id("rcc-confirm-button");
    // локаторы для проверки FAQ
    private final By FAQ = By.cssSelector("div.Home_SubHeader__zwi_E");
    private final By faqButtons = By.cssSelector("div.accordion__button");
    private final By faqPanels = By.cssSelector("div.accordion__panel");

    //конструктор
    public HomePageSamokat(WebDriver driver) {
        this.driver = driver;
    }
    // метод ожидания появления кнопки куки и нажатия на неё
    public void acceptCookiesIfVisible() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookie = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));

        Actions actions = new Actions(driver);
        actions.moveToElement(cookie).click().perform();

    }

    // методы используемые в тесте проверки работы кнопок заказать
    public boolean isUpperOrderButtonDisplayed() {
        return driver.findElement(upperOrderButton).isDisplayed();
    }

    public boolean isLowerOrderButtonDisplayed() {
        return driver.findElement(lowerOrderButton).isDisplayed();
    }

    public FormFirstPage upperOrderButtonPress(){
        driver.findElement(upperOrderButton).click();
        return new FormFirstPage(driver);
    }

    public FormFirstPage lowerOrderButtonPress(){
        driver.findElement(lowerOrderButton).click();
        return new FormFirstPage(driver);
    }

    public void waitUntilUpperOrderButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(upperOrderButton));
    }

    public void waitUntilLowerOrderButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.visibilityOfElementLocated(lowerOrderButton));
    }

    // далее методы используемые для проверки FAQ
    public void scrollIntoFAQSection() {
    WebElement faqElement = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(FAQ));

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqElement);

}

    public WebElement getFAQButton(int index) {
        return driver.findElements(faqButtons).get(index);
    }

    public WebElement getFAQPanel(int index) {
        return driver.findElements(faqPanels).get(index);
    }

    public String getFAQButtonText(int index) {
        return getFAQButton(index).getText().trim();
    }

    public String getFAQPanelText(int index) {
        return getFAQPanel(index).getText().trim();
    }
}
