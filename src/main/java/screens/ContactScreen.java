package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class ContactScreen extends BaseScreen{

    public ContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    AndroidElement headerContactScreen;

    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    AndroidElement btnAddNewContact;

    public boolean validateHeader(){
        return textInElementPresent(headerContactScreen, "Contact list", 5);
    }

    public void cliclBtnAddNewContact(){
        clickWait(btnAddNewContact,5);
    }

    public boolean validatePopUpMessage(){
        return textInElementPresent(popUpMessage, "Contact was added!",6);
    }
}
