package page;

import org.example.MyConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver webDriver;

    // Кнопка "Заказать" вверху страницы
    @FindBy(xpath = "//div[contains(@class, 'Header')]/button[text()='Заказать']")
    private WebElement topOrderButton;

    // Кнопка "Заказать" внизу страницы
    @FindBy(xpath = "//div[contains(@class, 'FinishButton')]/button[text()='Заказать']")
    private WebElement bottomOrderButton;

    // Кнобка согласия на куки
    @FindBy(id = "rcc-confirm-button")
    private WebElement confirmCookieButton;

    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver,
                MyConfiguration.DEFAULT_WAITING_SEC), this);
    }

    public void clickOrderButton(OrderButtonPosition position){
        switch (position) {
            case TOP:
                topOrderButton.click();
                break;
            case BOTTOM:
                // Сначала скроллим вниз
                ((JavascriptExecutor) webDriver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight)");
                bottomOrderButton.click();
                break;
        }
    }

    public String GetAnswer(String questionText) {
        // Скроллим вниз
        ((JavascriptExecutor) webDriver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        // Находим вопрос по тексту
        WebElement questionElement = webDriver.findElement(
                By.xpath("//div[@class='accordion__button'][contains(text(), '" + questionText + "')]"));
        String questionId = questionElement.getAttribute("id");
        questionElement.click();
        // Ищем соответствующий ответ по Id
        // Пример accordion__heading-0 -> accordion__panel-0
        String answerId = questionId.replaceAll("heading", "panel");
        WebElement answerElement = webDriver.findElement(By.id(answerId));

        return answerElement.getText();
    }

    public void clickConfirmCookieButton(){
        confirmCookieButton.click();
    }

    public enum OrderButtonPosition {
        TOP,
        BOTTOM
    }
}
