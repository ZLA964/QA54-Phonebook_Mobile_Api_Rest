package tests_ui;

import config.AppiumConfig;
import dto.ContactDtoLombok;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helpers.RandomUtils.*;
import static helpers.RandomUtils.generateString;

public class EditContactTests extends AppiumConfig {
    AuthenticationScreen authenticationScreen;
    ContactScreen contactScreen;
    AddNewContactScreen addNewContactScreen;
    UserDtoLombok user = UserDtoLombok.builder()
            .username("6mp5lj4vrs@example.com")
            .password("Poiuyt123!")
            .build();

    ContactDtoLombok contact = ContactDtoLombok.builder()
            .name("$$$"+generateString(4))
            .lastName("$$$" + generateString(7))
            .email("$$$" + generateEmail(6))
            .phone(generatePhone(11))
            .address(generateString(7))
            .description("$$$" + generateString(25))
            .build();

    @BeforeMethod
    public void openContactList(){
        new SplashScreen(driver).goToAuthScreen(6);
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthentication(user);
        authenticationScreen.clickBtnLogin();
        contactScreen = new ContactScreen(driver);
    }

    @Test
    public void editContactPositiveTest() {
        contactScreen.gotoEditScreen();
        EditContactScreen editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contact);
        Assert.assertTrue(contactScreen.validatePopUpMessage("Contact was updated!"));
    }

    @Test
    public void editContactNegativeTest() {
        ContactDtoLombok contactN = ContactDtoLombok.builder()
                .name("")
                .lastName("$$$" + generateString(7))
                .email("$$$" + generateEmail(6))
                .phone(generatePhone(11))
                .address(generateString(7))
                .description("$$$" + generateString(25))
                .build();
        contactScreen.gotoEditScreen();
        EditContactScreen editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contactN);
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("{name=must not be blank}",3));
    }
}
