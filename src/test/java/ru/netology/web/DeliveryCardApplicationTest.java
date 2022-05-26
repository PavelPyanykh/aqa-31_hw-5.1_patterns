package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.DataGenerator.Registration.*;

public class DeliveryCardApplicationTest {

    @BeforeEach
    void setUpTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitApplication() {
        RegistrationInfo userData = generateInfo();
        String initialDate = generateDate(3);
        String rescheduledDate = generateDate(5);
        $("[data-test-id='city'] input").setValue(userData.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(initialDate);
        $("[data-test-id='name'] input").setValue(userData.getName());
        $("[data-test-id='phone'] input").setValue(userData.getPhone());
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + initialDate), Duration.ofSeconds(15));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(rescheduledDate);
        $(withText("Запланировать!")).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + rescheduledDate), Duration.ofSeconds(15));
    }
}
