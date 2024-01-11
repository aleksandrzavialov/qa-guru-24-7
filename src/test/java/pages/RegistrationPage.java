import com.codeborne.selenide.SelenideElement;
import components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            tableTitle = $("#example-modal-sizes-title-lg"),
            tableContent = $(".table-responsive"),
            closeTableButton = $("#closeLargeModal"),
            formWrapper = $(".practice-form-wrapper");

    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        formWrapper.shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWrapper.$(byText(gender)).parent().click();

        return this;
    }

    public RegistrationPage setUserNumber(String phone) {
        userNumberInput.setValue(phone);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, int year) {
        calendarComponent.setCalendarDate(year, month, day);

        return this;
    }

    public RegistrationPage setSubjectWithFullName(String subject) {
        subjectInput.setValue(subject).pressEnter();

        return this;
    }

    public RegistrationPage setSubjectWithPartialName(String subject) {
        subjectInput.setValue(subject.substring(0, 1));
        $(withText(subject.substring(1, subject.length()))).click();

        return this;
    }

    public RegistrationPage setAllSubjects(String[] subjects) {
        for (String subject : subjects) {
            subjectInput.setValue(subject).pressEnter();
        }

        return this;
    }

    public RegistrationPage setHobbies(String[] hobbiesList) {
        for (String hobby : hobbiesList) {
            $(byText(hobby)).parent().click();
        }
        return this;
    }

    public RegistrationPage uploadPicture(String picture) {
        pictureInput.uploadFromClasspath(picture);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateInput.click();
        stateInput.$(byText(state)).click();

        return this;

    }

    public RegistrationPage setCity(String city) {
        cityInput.click();
        cityInput.$(byText(city)).click();

        return this;
    }

    public void submitResult() {
        submitButton.click();
    }

    public RegistrationPage checkResultTableUI() {
        tableTitle.shouldHave(text("Thanks for submitting the form"));
        closeTableButton.shouldBe(visible);

        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        tableContent.$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }

    public void checkTableIsNotVisible() {
        formWrapper.shouldHave(text("Student Registration Form"));
        tableTitle.shouldNotBe(visible);
        tableContent.shouldNotBe(visible);
        closeTableButton.shouldNotBe(visible);
    }


    public void fillAllStudentFields(Student student) {
        setFirstName(student.name);
        setLastName(student.lastName);
        setEmail(student.email);
        setGender(student.gender);
        setUserNumber(student.phone).
                setDateOfBirth(student.dayOfBirth, student.monthOfBirth, student.yearOfBirth).
                setAllSubjects(student.subjects).
                setHobbies(student.hobbies).
                uploadPicture(student.picture).
                setAddress(student.address).
                setState(student.state).
                setCity(student.city);
    }

    public void checkAllStudentFields(Student student) {
        checkResult("Student Name", student.name + " " + student.lastName).
                checkResult("Student Email", student.email).
                checkResult("Gender", student.gender).
                checkResult("Mobile", student.phone).
                checkResult("Date of Birth",
                        student.dayOfBirth + " " + student.monthOfBirth + "," + student.yearOfBirth).
                checkResult("Subjects", String.join(", ", student.subjects)).
                checkResult("Hobbies", String.join(", ", student.hobbies)).
                checkResult("Picture", student.picture).
                checkResult("Address", student.address).
                checkResult("State and City", student.state + " " + student.city);
    }
}