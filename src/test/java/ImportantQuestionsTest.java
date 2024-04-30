import config.MyConfiguration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.*;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Сколько это стоит? И как оплатить?",
                                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Хочу сразу несколько самокатов! Так можно?",
                                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Как рассчитывается время аренды?",
                                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Можно ли заказать самокат прямо на сегодня?",
                                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Можно ли продлить заказ или вернуть самокат раньше?",
                                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Вы привозите зарядку вместе с самокатом?",
                                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Можно ли отменить заказ?",
                                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                "Я жизу за МКАДом, привезёте?",
                                "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Сколько это стоит? И как оплатить?",
                                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Хочу сразу несколько самокатов! Так можно?",
                                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Как рассчитывается время аренды?",
                                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Можно ли заказать самокат прямо на сегодня?",
                                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Можно ли продлить заказ или вернуть самокат раньше?",
                                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Вы привозите зарядку вместе с самокатом?",
                                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Можно ли отменить заказ?",
                                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                "Я жизу за МКАДом, привезёте?",
                                "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                        },
        });
    }

    private WebDriver webDriver;
    private final WebDriverFactory.BrowserType browserType;
    private final String question;
    private final String expectedAnswer;

    public ImportantQuestionsTest(WebDriverFactory.BrowserType browserType, String question, String expectedAnswer) {
        this.browserType = browserType;
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    @Before
    public void setup() throws Exception {
        webDriver = WebDriverFactory.getWebDriver(browserType);
        webDriver.get(MyConfiguration.PAGE_URL);
    }

    @Test
    public void checkQuestions() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickConfirmCookieButton();
        String actualAnswer = mainPage.GetAnswer(question);
        Assert.assertThat(actualAnswer, containsString(expectedAnswer));
    }

    @After
    public void TearDown() {
        webDriver.quit();
    }
}
