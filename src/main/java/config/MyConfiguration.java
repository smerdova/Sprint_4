package config;

import factory.WebDriverFactory;

public abstract class MyConfiguration {
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public static final int DEFAULT_WAITING_SEC = 15;

    public static final WebDriverFactory.BrowserType BROWSER_TYPE = WebDriverFactory.BrowserType.FIREFOX;
}
