package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.TestNGListener;

import java.lang.reflect.Method;

import static utils.UserFactory.positiveUser;

@Listeners(TestNGListener.class)
public class RegistrationTests extends ApplicationManager {

    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void goToRegPage() {
        new HomePage(getDriver()).clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }

    @Test(groups = {"smoke", "user"})
    public void regPositiveTest(Method method) {
        User user = positiveUser();
        logger.info("Start test " + method.getName() + "with data " + user);

        loginPage.typeRegForm(user);

        logger.info("End test " + method.getName() + "with data " + user);

        Assert.assertTrue(new ContactsPage(getDriver()).isTextNoContactsPresent("No Contacts here!"));
    }

    @Test(groups = {"negative"})
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
