package ru.netology.form;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFormTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldSendOrderForm() {
        String planningDate = generateDate(4);
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("Уфа");
        form.$("[data-test-id='date'] input").doubleClick();
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(planningDate);
        form.$("[data-test-id='name'] input").setValue("Кузьма Петров-Водкин");
        form.$("[data-test-id='phone'] input").setValue("+79991233210");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='notification']").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}