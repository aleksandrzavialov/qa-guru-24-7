import org.junit.jupiter.api.Test;

public class DemoQAFullFormTest extends TestBase {
    @Test
    void successfulRegisterFullDataTest() {
        String name = "Anna";
        String lastName = "Ivanova";
        String email = "aivanova@mail.ru";
        String gender = "Female";
        String phone = "9999123456";
        int yearOfBirth = 2001;
        String monthOfBirth = "March";
        String dayOfBirth = "27";
        String[] subjects = new String[]{"Maths", "Economics"};
        String[] hobbies = new String[]{"Reading", "Music"};
        String picture = "Capibara.jpg";
        String address = "Lenina 169 - 228";
        String state = "Uttar Pradesh";
        String city = "Merrut";

        Student student = new Student(name, lastName, email, gender, phone, yearOfBirth, monthOfBirth, dayOfBirth,
                subjects, hobbies, picture, address, state, city);

        //Preconditions
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openPage();

        //Form filling
        registrationPage.fillAllStudentFields(student);
        registrationPage.submitResult();

        //Checking submit results
        registrationPage.checkResultTableUI().
                checkAllStudentFields(student);
    }

    @Test
    void successfulRegisterMinimalDataTest() {
        String name = "Anna"; //
        String lastName = "Ivanova";
        String gender = "Female";
        String phone = "9999123456";
        int yearOfBirth = 2001;
        String monthOfBirth = "March";
        String dayOfBirth = "27";

        //Preconditions
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openPage();

        //Form filling
        registrationPage.setFirstName(name).
                setLastName(lastName).
                setGender(gender).
                setUserNumber(phone).
                setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);

        registrationPage.submitResult();

        //Checking submit results
        registrationPage.checkResultTableUI().
                checkResult("Student Name", name + " " + lastName).
                checkResult("Student Email", " ").
                checkResult("Gender", gender).
                checkResult("Mobile", phone).
                checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth).
                checkResult("Subjects", " ").
                checkResult("Hobbies", " ").
                checkResult("Picture", " ").
                checkResult("Address", " ").
                checkResult("State and City", " ");

    }

    @Test
    void unsuccessfulRegisterNoGenderEnteredTest() {
        String name = "Anna"; //
        String lastName = "Ivanova";
        String phone = "9999123456";
        int yearOfBirth = 2001;
        String monthOfBirth = "March";
        String dayOfBirth = "27";

        //Preconditions
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.openPage();

        //Form filling
        registrationPage.setFirstName(name).
                setLastName(lastName).
                setUserNumber(phone).
                setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);

        registrationPage.submitResult();

        //Checking submit results
        registrationPage.checkTableIsNotVisible();

    }

}


