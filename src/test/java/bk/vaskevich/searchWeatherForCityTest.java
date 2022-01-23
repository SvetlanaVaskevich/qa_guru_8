package bk.vaskevich;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class searchWeatherForCityTest {


    @ParameterizedTest(name = "search weather Ural city: {0}")
    @CsvSource(value = {
            "Екатеринбург, Погода в Екатеринбурге",
            "Нижний Тагил, Погода в Нижнем Тагиле"
    })
    public void serchWeatherUralCitiesTest(String city,String expected){
        open("https://rp5.ru/");
        $("#searchStr").setValue(city);
        $("#searchButton").click();
        $(".innerTextResult a").click();
        $("#pointNavi-container h1").shouldHave(Condition.text(expected));
    }

    static Stream<Arguments> nameCity() {
        return Stream.of(
                Arguments.of("Москва","Погода в Москве"),
                Arguments.of("Псков","Погода в Пскове")
        );
    }

    @ParameterizedTest()
    @MethodSource("nameCity")
    public void serchWeatherCitiesTest(String city,String expected){
        open("https://rp5.ru/");
        $("#searchStr").setValue(city);
        $("#searchButton").click();
        $(".innerTextResult a").click();
        $("#pointNavi-container h1").shouldHave(Condition.text(expected));
    }
}

