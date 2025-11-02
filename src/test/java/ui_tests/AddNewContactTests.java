package ui_tests;

import dto.Contact;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ContactFactory;
import utils.HeaderMenuItem;

import static pages.BasePage.*;

public class AddNewContactTests extends ApplicationManager {

    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int numberOfContacts;

    @BeforeMethod
    public void login() throws IllegalAccessException {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm("lizkatest@mail.ru", "wertY!23");
        contactsPage = new ContactsPage(getDriver());
        numberOfContacts = contactsPage.getNumberOfContacts();
        addPage = clickButtonHeader(HeaderMenuItem.ADD);
    }

    @Test
    public void addNewContactPos() {
        addPage.typeContactForm(ContactFactory.positiveContact());
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd, numberOfContacts + 1);
    }

    @Test
    public void addNewContactPositiveTestValidateList() {
        Contact contact = ContactFactory.positiveContact();
        addPage.typeContactForm(contact);
        contactsPage.clickLastContact();
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }
}
