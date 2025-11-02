package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static utils.UserFactory.positiveUser;

public class RegistrationTests extends ApplicationManager {

    LoginPage loginPage;

    @BeforeMethod
    public void goToRegPage() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void regPositiveTest() {
        User user = positiveUser();
        loginPage.typeRegForm(user);
        Assert.assertTrue(new ContactsPage(getDriver()).isTextNoContactsPresent("No Contacts here!"));
    }

    @Test
    public void regNegativeTestWrongEmail() {
        User user = positiveUser();
        user.setUsername("wrong email");
        loginPage.typeRegForm(user);
        Assert.assertTrue(loginPage.closeAlertReturnText().contains("Wrong email or password format"));

    }

    @Test
    public void regNegativeTestLongPassword() {
        User user = new User("lizkatest11@mail.ru", "xt4/a]2eC_5#e^79");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.typeRegistrationForm(user);
        Assert.assertTrue(registrationPage.regIsFailed());
    }
}
