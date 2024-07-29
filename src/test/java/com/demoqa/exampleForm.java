package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class exampleForm {
    @BeforeAll
    static void config (){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void submitPracticeForm() {
        String firstName = "Sergei";
        String lastName = "Gubin";
        String email = "gybins@yandex.ru";
        String mobileNumber = "7707433123";
        String gender = "Male";
        String month = "November";
        String year = "1996";
        String subject1 = "Maths";
        String subject2 = "Sports";
        String currentAddress = "Almaty";
        String nameFile = "image.png";
        String state = "NCR";
        String city = "Noida";

        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //add personal information
        $("#firstName").sendKeys(firstName);
        $("#lastName").sendKeys(lastName);
        $("#userEmail").sendKeys(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobileNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $(".react-datepicker__year-select").selectOptionContainingText(year);
        $(".react-datepicker__day.react-datepicker__day--025").click();

        $("#subjectsInput").sendKeys("M");
        $(byText(subject1)).click();
        $(byText(subject2)).click();

        File file = new File("src/resources/" +nameFile);
        $("#uploadPicture").uploadFile(file);
        $("#uploadPicture").shouldHave(value(nameFile));

        $("#currentAddress").sendKeys(currentAddress);
        $(byText("Select State")).click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();

        $("#submit").click();

        //CHECK
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $(".table").$(byText("Student Email")).sibling(0).shouldHave(text(email));
        $(".table").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $(".table").$(byText("Mobile")).sibling(0).shouldHave(text(mobileNumber));
        $(".table").$(byText("Date of Birth")).sibling(0).shouldHave(text("25 "+ month + "," + year));
        $(".table").$(byText("Subjects")).sibling(0).shouldHave(text(subject1));
        $(".table").$(byText("Hobbies")).sibling(0).shouldHave(text(subject2));
        $(".table").$(byText("Picture")).sibling(0).shouldHave(text(nameFile));
        $(".table").$(byText("Address")).sibling(0).shouldHave(text(currentAddress));
        $(".table").$(byText("State and City")).sibling(0).shouldHave(text(state + " " + city));










    }
}
