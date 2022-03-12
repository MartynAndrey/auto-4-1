package ru.netology.form;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryFormTest {

    private String getValidDate() {
        Date currentDate = new Date();
        Date validDate = new Date(currentDate.getTime() + 60*60*24*1000*4);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(validDate);
    }

    @Test
    public void shouldSendOrderForm() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input").setValue("Уфа");
        form.$("[data-test-id='date'] input").doubleClick();
        form.$("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(getValidDate());
        form.$("[data-test-id='name'] input").setValue("Кузьма Петров-Водкин");
        form.$("[data-test-id='phone'] input").setValue("+79991233210");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}