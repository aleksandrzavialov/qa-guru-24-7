package components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setCalendarDate(int year, String month, String date) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(byText(Integer.toString(year))).click();
        $(".react-datepicker__month-select").click();
        $(byText(month)).click();
        String dateSelector = ".react-datepicker__day--0" + date;
        $(dateSelector + "[aria-label *= " + month + "]").click();
    }
}
