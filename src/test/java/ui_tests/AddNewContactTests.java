package ui_tests;

import data_providers.ContactDP;
import dto.Contact;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ContactFactory;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.PropertiesReader.*;

@Listeners(TestNGListener.class)
public class AddNewContactTests extends ApplicationManager {

    SoftAssert softAssert = new SoftAssert();

    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;
    int numberOfContacts;

    @BeforeMethod(alwaysRun = true)
    public void login() throws IllegalAccessException {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(getProperty("base.properties", "login"), getProperty("base.properties", "password"));
        contactsPage = new ContactsPage(getDriver());
        numberOfContacts = contactsPage.getNumberOfContacts();
        addPage = clickButtonHeader(HeaderMenuItem.ADD);
    }

    @Test(groups = {"smoke", "contact"})
    public void addNewContactPos() {
        addPage.typeContactForm(ContactFactory.positiveContact());
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd, numberOfContacts + 1);
    }

    @Test(dataProvider = "dataProviderContactFile", dataProviderClass = ContactDP.class)
    public void addNewContactPosDataProvider(Contact contact) {
        addPage.typeContactForm(contact);
        int numberOfContactsAfterAdd = contactsPage.getNumberOfContacts();
        Assert.assertEquals(numberOfContactsAfterAdd, numberOfContacts + 1);
    }

    @Test
    public void addNewContactPositiveTestValidateList() {
        Contact contact = ContactFactory.positiveContact();
        addPage.typeContactForm(contact);
        //contactsPage.clickLastContact();
        Assert.assertTrue(contactsPage.isContactPresent(contact));
    }

    @Test
    public void addNewContactPositiveTest_ValidateElementSCROLL() {
        Contact contact = ContactFactory.positiveContact();
        addPage.typeContactForm(contact);
        contactsPage.scrollToLastElList();
        //contactsPage.scrollToLastElJS();
        contactsPage.clickLastContact();
        String text = contactsPage.getContactCardText();
        softAssert.assertTrue(text.contains(contact.getName()));
        softAssert.assertTrue(text.contains(contact.getLastname()));
        softAssert.assertTrue(text.contains(contact.getPhone()));
        softAssert.assertTrue(text.contains(contact.getEmail()));
        softAssert.assertTrue(text.contains(contact.getAddress()));
        softAssert.assertAll();
    }
}
