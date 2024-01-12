package tests;

import data.StudentData;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class DemoQATextBoxTest extends TestBase {

    @Test
    void fillTextBoxFormTest() {

        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage.openPage();

        textBoxPage.setFullName(StudentData.name);
        textBoxPage.setEmail(StudentData.email);
        textBoxPage.setCurrentAddress(StudentData.currentAddress);
        textBoxPage.setPermanentAddress(StudentData.permanentAddress);
        textBoxPage.submitResult();

        textBoxPage.checkNameResult(StudentData.name);
        textBoxPage.checkEmailResult(StudentData.email);
        textBoxPage.checkCurrentAddressResult(StudentData.currentAddress);
        textBoxPage.checkPermanentAddressResult(StudentData.permanentAddress);

    }
}