package org.example.tests;
import org.example.helpers.HomePageSamokat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


@RunWith(Parameterized.class)
public class ParameterizedFaqTest extends BaseTest {


        private final int questionNumber;
        private final String expectedQuestionText;
        private final String expectedQuestionAnswer;

        public ParameterizedFaqTest(int questionNumber, String expectedQuestionText, String expectedQuestionAnswer) {
            this.questionNumber = questionNumber;
            this.expectedQuestionText = expectedQuestionText;
            this.expectedQuestionAnswer = expectedQuestionAnswer;

        }

        @Parameterized.Parameters
        public static Object[][] testData() {
            return new Object[][]{
                    {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
            };
        }

        @Test
        public void checkFaqQuestionsAndAnswers() {
            HomePageSamokat objHomePage = new HomePageSamokat(driver);
            objHomePage.scrollIntoFAQSection();
            String actualHeading = objHomePage.getFAQButtonText(questionNumber);

            Assert.assertEquals("Заголовок вопроса FAQ не совпадает для индекса " + questionNumber,
                    expectedQuestionText, actualHeading);
            WebElement button = objHomePage.getFAQButton(questionNumber);
            button.click();

            WebElement panel = objHomePage.getFAQPanel(questionNumber);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(panel));

            Assert.assertEquals("Ответ FAQ не совпадает для индекса " + questionNumber,
                    expectedQuestionAnswer, objHomePage.getFAQPanelText(questionNumber));


        }
    }
