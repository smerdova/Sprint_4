package page;

import org.example.MyConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrderStepOnePage {
    private final WebDriver webDriver;

    // Имя
    @FindBy(xpath = "//input[@placeholder='* Имя']")
    private WebElement nameInput;

    // Фамилия
    @FindBy(xpath = "//input[@placeholder='* Фамилия']")
    private WebElement surnameInput;

    // Адрес
    @FindBy(xpath = "//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressInput;

    // Метро
    @FindBy(xpath = "//input[@placeholder='* Станция метро']")
    private WebElement metroInput;

    // Телефон
    @FindBy(xpath = "//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneInput;

    // Кнопка "Далее"
    @FindBy(xpath = "//button[text()='Далее']")
    private WebElement nextButton;

    public OrderStepOnePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    public void setName(String value) {
        nameInput.sendKeys(value);
    }

    public void setSurname(String value) {
        surnameInput.sendKeys(value);
    }

    public void setAddress(String value) {
        addressInput.sendKeys(value);
    }

    public void setMetro(String name, MetroColor color) {
        metroInput.click();
        metroInput.sendKeys(name);

        // Находим станцию не только по имени, но и по цвету
        WebElement station = webDriver.findElement(
                By.xpath("//div[contains(@style, '" + color + "')]"));
        station.click();
    }

    public void setPhone(String value) {
        phoneInput.sendKeys(value);
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public enum MetroColor {

        BLUE("rgb(77, 198, 244)"),
        DARK_BLUE("rgb(44, 117, 196)");
        private String rgbString;

        MetroColor(String rgbString) {
            this.rgbString = rgbString;
        }

        @Override
        public String toString() {
            return rgbString;
        }
    }
}
