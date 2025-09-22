package org.example.helpers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;

public class HomePageSamokat {
    private WebDriver driver;
    public static final String PAGEURL= "https://qa-scooter.praktikum-services.ru/";

    // локатор верхней и нижней кнопок "Заказать"
    private By upperOrderButton= By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']");
    private By lowerOrderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    //локатор кнопки принятия куки
    private By cookieButton = By.id("rcc-confirm-button");
    // локаторы для проверки FAQ
    private final By FAQ = By.cssSelector("div.Home_SubHeader__zwi_E");
    //private By faqHeader = By.cssSelector("div.Home_SubHeader__zwi_E");
    private By faqButtons = By.cssSelector("div.accordion__button");
    private By faqPanels = By.cssSelector("div.accordion__panel");

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

    public LoginToOrder upperOrderButtonPress(){
        driver.findElement(upperOrderButton).click();
        return new LoginToOrder(driver);
    }

    public LoginToOrder lowerOrderButtonPress(){
        driver.findElement(lowerOrderButton).click();
        return new LoginToOrder(driver);
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
