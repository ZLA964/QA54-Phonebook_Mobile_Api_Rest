package tests_ui;

import config.AppiumConfig;
import dto.ContactDtoLombok;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helpers.RandomUtils.*;

public class AddContactTests extends AppiumConfig {
    AuthenticationScreen authenticationScreen;
    ContactScreen contactScreen;
    AddNewContactScreen addNewContactScreen;
    UserDtoLombok user = UserDtoLombok.builder()
            .username("6mp5lj4vrs@example.com")
            .password("Poiuyt123!")
            .build();

    @BeforeMethod
    public void openAddNewContactForm(){
        new SplashScreen(driver).goToAuthScreen(6);
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthentication(user);
        authenticationScreen.clickBtnLogin();
        contactScreen = new ContactScreen(driver);
        contactScreen.clickBtnAddNewContact();
        addNewContactScreen = new AddNewContactScreen(driver);
    }

    @Test(invocationCount = 3)
    public void addContactPositiveTest(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("@@@"+generateString(4))
                .lastName(generateString(10))
                .email(generateEmail(9))
                .phone(generatePhone(11))
                .address(generateString(7))
                .description(generateString(25))
                .build();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreateContact();
        Assert.assertTrue(new ContactScreen(driver).validatePopUpMessage());

    }

    @Test
    public void addContactNegativeTest_EmptyLastName(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("@@@"+generateString(4))
                .lastName(" ")
                .email(generateEmail(9))
                .phone(generatePhone(11))
                .address(generateString(7))
                .description(generateString(25))
                .build();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreateContact();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("{lastName=must not be blank}",6));


    }
}
