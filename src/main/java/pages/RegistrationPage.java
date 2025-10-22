package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "input[name='email']")
    WebElement inputEmail;

    @FindBy(css = "input[name='password']")
    WebElement inputPassword;

    @FindBy(css = "button[name='registration']")
    WebElement btnRegistrationForm;

    @FindBy(css = "a[href='/contacts']")
    WebElement regSuccess;

    @FindBy(css = "button[name='registration']")
    WebElement regFailed;

    public void typeRegistrationForm(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnRegistrationForm.click();
    }

    public boolean isRegDisplayed() {
        return elementIsDisplayed(regSuccess);
    }

    public boolean regIsFailed() {
        return elementIsDisplayed(regFailed);
    }

}
