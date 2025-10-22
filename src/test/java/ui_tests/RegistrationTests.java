package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void regPositiveTest() {
        User user = new User("lizkatest13@mail.ru", "Qwerty!23");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        Assert.assertTrue(registrationPage.isRegDisplayed());
    }

    @Test
    public void regNegativeTestShortPassword() {
        User user = new User("lizkatest10@mail.ru", "Q!1a");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        Assert.assertTrue(registrationPage.regIsFailed());
    }

    @Test
    public void regNegativeTestLongPassword() {
        User user = new User("lizkatest11@mail.ru", "xt4/a]2eC_5#e^79");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        Assert.assertTrue(registrationPage.regIsFailed());

        //BUG!!! PASSWORD is more than 15 symbols(16 char), BUT REGISTRATION WAS SUCCESSED
    }
}
