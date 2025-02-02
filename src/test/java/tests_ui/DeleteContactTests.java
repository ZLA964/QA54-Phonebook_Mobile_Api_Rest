package tests_ui;

import config.AppiumConfig;

import dto.UserDtoLombok;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactScreen;
import screens.SplashScreen;



public class DeleteContactTests extends AppiumConfig {

    AuthenticationScreen authenticationScreen;
    ContactScreen contactScreen;
    UserDtoLombok user = UserDtoLombok.builder()
            .username("6mp5lj4vrs@example.com")
            .password("Poiuyt123!")
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
    public void deleteFirstContactPositiveTest(){
        contactScreen.gdeleteContact();
        contactScreen.clickPopUpRemoveBtnYes();
    }


}
