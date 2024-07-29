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
        String mobileNumber = "77074331232";
        String gender = "Male";
        String month = "November";
        String year = "1996";
        String subject1 = "Maths";
        String subject2 = "Sports";
        String currentAddress = "Almaty";
        String nameFile = "image.png";
        String state = "null";
        String city = "Almaty";

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









    }
}
