package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;

public class LoginTests extends ApplicationManager {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm("lizkatest@mail.ru", "wertY!23");
        Assert.assertTrue(new ContactsPage(getDriver()).isTextContactsPresent("CONTACTS"));
    }

    @Test
    public void loginNegativeTest_wrongPassword() {
        User user = new User("lizkatest@mail.ru", "Qwerty!23");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }
}
