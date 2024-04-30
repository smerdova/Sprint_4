package page;

import config.MyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

// Страница подтверждения заказа
public class ConfirmPage {
    private final WebDriver webDriver;

    // Кнопка "Да"
    @FindBy(xpath = "//button[text()='Да']")
    private WebElement yesButton;

    public ConfirmPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    public void clickYesButton(){
        yesButton.click();
    }

}
