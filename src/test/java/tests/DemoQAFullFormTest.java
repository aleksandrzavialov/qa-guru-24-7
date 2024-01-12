package tests;

import data.StudentData;
import org.junit.jupiter.api.Test;
import models.Student;
import pages.RegistrationPage;
import pages.components.ResultTableComponent;


public class DemoQAFullFormTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableComponent resultTable = new ResultTableComponent();
    @Test
    void successfulRegisterFullDataTest() {

        Student student = new Student(StudentData.name, StudentData.lastName, StudentData.email, StudentData.gender,
                StudentData.phone, StudentData.yearOfBirth, StudentData.monthOfBirth, StudentData.dayOfBirth,
                StudentData.subjects, StudentData.hobbies, StudentData.picture, StudentData.address, 
                StudentData.state, StudentData.city);

        //Preconditions
        registrationPage.openPage();

        //Form filling
        registrationPage.fillAllStudentFields(student);
        registrationPage.submitResult();

        //Checking submit results
        resultTable.checkResultTableUI().
                checkAllStudentFields(student);
    }

    @Test
    void successfulRegisterMinimalDataTest() {

        //Preconditions
        registrationPage.openPage();

        //Form filling
        registrationPage.setFirstName(StudentData.name).
                setLastName(StudentData.lastName).
                setGender(StudentData.gender).
                setUserNumber(StudentData.phone).
                setDateOfBirth(StudentData.dayOfBirth, StudentData.monthOfBirth, StudentData.yearOfBirth);

        registrationPage.submitResult();

        //Checking submit results
        resultTable.checkResultTableUI().
                checkResult("Student Name", StudentData.name + " " + StudentData.lastName).
                checkResult("Student Email", " ").
                checkResult("Gender", StudentData.gender).
                checkResult("Mobile", StudentData.phone).
                checkResult("Date of Birth",
                        StudentData.dayOfBirth + " " + StudentData.monthOfBirth + "," + StudentData.yearOfBirth).
                checkResult("Subjects", " ").
                checkResult("Hobbies", " ").
                checkResult("Picture", " ").
                checkResult("Address", " ").
                checkResult("State and City", " ");

    }

    @Test
    void unsuccessfulRegisterNoGenderEnteredTest() {

        //Preconditions
        registrationPage.openPage();

        //Form filling
        registrationPage.setFirstName(StudentData.name).
                setLastName(StudentData.lastName).
                setUserNumber(StudentData.phone).
                setDateOfBirth(StudentData.dayOfBirth, StudentData.monthOfBirth, StudentData.yearOfBirth);

        registrationPage.submitResult();

        //Checking submit results
        resultTable.checkTableIsNotVisible();

    }

}


