package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HeaderMenuItem;

import java.time.Duration;

public abstract class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    public static <T extends BasePage> T clickButtonHeader(HeaderMenuItem item) throws IllegalAccessException {
//T = type; метод возвращает любой тип данных, но он ограничен(тем что является наследником БэйсПейдж. какую константу получит, такю вернет)
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(item.getLocator())));
        element.click();
        switch (item) {
            case HOME -> {
                return (T) new HomePage(driver);
            }
            case CONTACTS -> {
                return (T) new ContactsPage(driver);
            }
            case LOGIN -> {
                return (T) new LoginPage(driver);
            }
            case ADD -> {
                return (T) new AddPage(driver);
            }
            case ABOUT -> {
                return (T) new AboutPage(driver);
            }
            default -> throw new IllegalAccessException("Invalid parameter headerMenuItem");
        }
    }

    public boolean elementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        return element.getText().contains(text);
    }
}
