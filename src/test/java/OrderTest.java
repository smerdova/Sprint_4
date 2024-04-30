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
public class OrderTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                MainPage.OrderButtonPosition.BOTTOM,
                                "Василий",
                                "Чапаев",
                                "Улица Долорес Ибаррури, дом 15",
                                "Арбатская",
                                OrderStepOnePage.MetroColor.BLUE,
                                "+79121231212",
                                "01.01.2023",
                                "сутки",
                                OrderStepTwoPage.Color.BLACK,
                                "побыстрее!"
                        },
                        {
                                WebDriverFactory.BrowserType.FIREFOX,
                                MainPage.OrderButtonPosition.TOP,
                                "Иван",
                                "Грозный",
                                "Улица Самолётов и пилотов",
                                "Арбатская",
                                OrderStepOnePage.MetroColor.DARK_BLUE,
                                "+79121111111",
                                "01.01.2025",
                                "шестеро суток",
                                OrderStepTwoPage.Color.GREY,
                                "помедленнее!"
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                MainPage.OrderButtonPosition.BOTTOM,
                                "Василий",
                                "Чапаев",
                                "Улица Долорес Ибаррури, дом 15",
                                "Арбатская",
                                OrderStepOnePage.MetroColor.BLUE,
                                "+79121231212",
                                "01.01.2023",
                                "сутки",
                                OrderStepTwoPage.Color.BLACK,
                                "побыстрее!"
                        },
                        {
                                WebDriverFactory.BrowserType.CHROME,
                                MainPage.OrderButtonPosition.TOP,
                                "Иван",
                                "Грозный",
                                "Улица Самолётов и пилотов",
                                "Арбатская",
                                OrderStepOnePage.MetroColor.DARK_BLUE,
                                "+79121111111",
                                "01.01.2025",
                                "шестеро суток",
                                OrderStepTwoPage.Color.GREY,
                                "помедленнее!"
                        },
        });
    }

    private WebDriver webDriver;
    private final WebDriverFactory.BrowserType browserType;
    private final MainPage.OrderButtonPosition orderButtonPosition;
    private final String name;
    private final String surName;
    private final String address;
    private final String metroName;
    private final OrderStepOnePage.MetroColor metroColor;
    private final String phoneNumber;
    private final String whenDate;
    private final String duration;
    private final OrderStepTwoPage.Color color;
    private final String comment;

    public OrderTest(WebDriverFactory.BrowserType browserType, MainPage.OrderButtonPosition orderButtonPosition, String name, String surName, String address,
                     String metroName, OrderStepOnePage.MetroColor metroColor, String phoneNumber, String whenDate,
                     String duration, OrderStepTwoPage.Color color, String comment) {
        this.browserType = browserType;
        this.orderButtonPosition = orderButtonPosition;
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.metroName = metroName;
        this.metroColor = metroColor;
        this.phoneNumber = phoneNumber;
        this.whenDate = whenDate;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    @Before
    public void setup() throws Exception {
        webDriver = WebDriverFactory.getWebDriver(browserType);
        webDriver.get(MyConfiguration.PAGE_URL);
    }

    @Test
    public void order() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderButton(orderButtonPosition);

        OrderStepOnePage orderStepOnePage = new OrderStepOnePage(webDriver);
        orderStepOnePage.setName(name);
        orderStepOnePage.setSurname(surName);
        orderStepOnePage.setAddress(address);
        orderStepOnePage.setMetro(metroName, metroColor);
        orderStepOnePage.setPhone(phoneNumber);
        orderStepOnePage.clickNextButton();

        OrderStepTwoPage orderStepTwoPage = new OrderStepTwoPage(webDriver);
        orderStepTwoPage.setWhen(whenDate);
        orderStepTwoPage.setDuration(duration);
        orderStepTwoPage.setColor(color);
        orderStepTwoPage.setComment(comment);
        orderStepTwoPage.clickOrderButton();

        ConfirmPage confirmPage = new ConfirmPage(webDriver);
        confirmPage.clickYesButton();

        OrderConfirmedPage orderConfirmedPage = new OrderConfirmedPage(webDriver);
        String status = orderConfirmedPage.getHeaderText();
        Assert.assertThat(status, containsString("Заказ оформлен"));
    }

    @After
    public void TearDown() {
        webDriver.quit();
    }
}
