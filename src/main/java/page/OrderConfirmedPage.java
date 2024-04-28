package page;

import org.example.MyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmedPage {
    private final WebDriver webDriver;

    // Заголовок
    @FindBy(xpath = "//div[contains(@class, 'Order_ModalHeader')]")
    private WebElement header;

    // Кнопка "Посмотреть статус"
    @FindBy(xpath = "//button[text()='Посмотреть статус']")
    private WebElement buttonShowStatus;

    public OrderConfirmedPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    public String getHeaderText(){
        return header.getText();
    }

}
