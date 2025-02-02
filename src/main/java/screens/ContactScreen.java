package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;

public class ContactScreen extends BaseScreen {

    public ContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    AndroidElement headerContactScreen;

    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    AndroidElement btnAddNewContact;

    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    AndroidElement firstContact;

    @FindBy(id = "android:id/button1")
    AndroidElement btnPopupYesRemove;

    int yLeftUpCorner = firstContact.getLocation().getY();
    int height = firstContact.getSize().getHeight();
    int width = firstContact.getSize().getWidth();
    TouchAction<?> touchAction = new TouchAction<>(driver);

    public void gotoEditScreen() {
        System.out.println("Y-> " + firstContact.getLocation().getY());
        System.out.println("x-> " + firstContact.getLocation().getX());
        System.out.println("height-> " + firstContact.getSize().getHeight());
        System.out.println("width-> " + firstContact.getSize().getWidth());

        touchAction.longPress(PointOption.point(width / 6 * 5, yLeftUpCorner + height))
                .moveTo(PointOption.point(width / 6, yLeftUpCorner + height))
                .release().perform();

    }

    public void gdeleteContact() {
        touchAction.longPress(PointOption.point(width / 6, yLeftUpCorner + height))
                .moveTo(PointOption.point(width / 6 * 5, yLeftUpCorner + height))
                .release().perform();
    }

    public void clickPopUpRemoveBtnYes(){
        clickWait(btnPopupYesRemove,5);
    }


    public boolean validateHeader() {
        return textInElementPresent(headerContactScreen, "Contact list", 5);
    }

    public void clickBtnAddNewContact() {
        clickWait(btnAddNewContact, 5);
    }

    public boolean validatePopUpMessage() {
        return textInElementPresent(popUpMessage, "Contact was added!", 6);
    }

    public boolean validatePopUpMessage(String text) {
        return textInElementPresent(popUpMessage, text, 6);
    }


}
