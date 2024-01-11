
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class DemoQATextBoxTest extends TestBase {

    @Test
    void fillTextBoxFormTest() {
        String name = "Alex";
        String email = "alex@egorov.com";
        String currentAddress = "Some street 1";
        String permanentAddress = "Another street 1";

        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage.openPage();
        textBoxPage.setFullName(name);
        textBoxPage.setEmail(email);
        textBoxPage.setCurrentAddress(currentAddress);
        textBoxPage.setPermanentAddress(permanentAddress);
        textBoxPage.submitResult();

        textBoxPage.checkNameResult(name);
        textBoxPage.checkEmailResult(email);
        textBoxPage.checkCurrentAddressResult(currentAddress);
        textBoxPage.checkPermanentAddressResult(permanentAddress);

    }
}