package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactScreen;
import screens.ErrorScreen;
import screens.SplashScreen;

import static helpers.RandomUtils.generateEmail;

public class LoginTests extends AppiumConfig {

    AuthenticationScreen authenticationScreen;


    @BeforeMethod
    public void openLoginForm() {
        new SplashScreen(driver).goToAuthScreen(6);
        authenticationScreen = new AuthenticationScreen(driver);
    }

    @Test
    public void loginPositiveTest(){
        UserDtoLombok user = UserDtoLombok.builder()
                .username("6mp5lj4vrs@example.com")
                .password("Poiuyt123!")
                .build();
        authenticationScreen.typeAuthentication(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ContactScreen(driver).validateHeader());
    }

    @Test
    public void loginNegative_unRegisteredUser_Test(){
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateEmail(9))
                .password("Poiuyt123!")
                .build();
        authenticationScreen.typeAuthentication(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect",5));
    }

    @Test
    public void loginNegative_wrongPassword_Test(){
        UserDtoLombok user = UserDtoLombok.builder()
                .username("6mp5lj4vrs@example.com")
                .password("Pouydst123!")
                .build();
        authenticationScreen.typeAuthentication(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect",5));
    }
}
