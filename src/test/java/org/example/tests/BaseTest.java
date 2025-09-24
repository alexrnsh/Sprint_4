package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pages.HomePageSamokat;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.example.constants.Constants.PAGE_URL;


public class BaseTest {

    WebDriver driver;

    HomePageSamokat homePage;

    @Before
    public void setUp() {
        driver = getDriver(DriverType.CHROME);
        homePage = new HomePageSamokat(driver);
        driver.get(PAGE_URL);
        homePage.acceptCookiesIfVisible();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    private WebDriver getDriver(DriverType driverType) {
        if (driverType == DriverType.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    enum DriverType {
        CHROME, FIREFOX
    }
}
