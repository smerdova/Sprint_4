package page;

import org.example.MyConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderStepTwoPage {
    private final WebDriver webDriver;

    // Когда привезти
    @FindBy(xpath = "//input[@placeholder='* Когда привезти самокат']")
    private WebElement whenInput;

    // Срок
    @FindBy(xpath = "//div[text()='* Срок аренды']")
    private WebElement durationInput;

    // Серый цвет
    @FindBy(id = "grey")
    private WebElement greyColorLabel;

    // Черный цвет
    @FindBy(id = "black")
    private WebElement blackColorLabel;

    // Комментарий
    @FindBy(xpath = "//input[@placeholder='Комментарий для курьера']")
    private WebElement commentInput;

    // Кнопка "Заказать"
    @FindBy(xpath = "//div[contains(@class, 'Order')]/button[text()='Заказать']")
    private WebElement orderButton;

    public OrderStepTwoPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    public void setWhen(String value) {
        whenInput.sendKeys(value, Keys.ENTER);
    }

    public void setDuration(String value) {
        durationInput.click();
        WebElement duration = webDriver.findElement(
                By.xpath("//div[text()='" + value + "']"));
        duration.click();
    }

    public void setColor(Color color) throws Exception {
        switch (color) {
            case GREY :
                greyColorLabel.click();
                break;
            case BLACK :
                blackColorLabel.click();
                break;
            default:
                throw new Exception("Неизвестный цвет");
        }
    }

    public void setBlackColor() {
        blackColorLabel.click();
    }

    public void setComment(String value) {
        commentInput.sendKeys(value);
    }

    public void clickOrderButton() {
        orderButton.click();
    }

    public void waitForLoading(int timeoutSec) {
        new WebDriverWait(webDriver, Duration.ofSeconds(timeoutSec))
                .until(ExpectedConditions.elementToBeClickable(orderButton));
    }

    public enum Color {
        BLACK,
        GREY
    }
}
