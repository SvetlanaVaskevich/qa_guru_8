package bk.vaskevich;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class KinopoiskSearchSerialTest {

    @BeforeEach
    public void openPage(){
      open("https://www.ivi.ru/");
    }

    @ValueSource(strings = {"Дивный новый мир,divnyij-novyij-mir,Экранизация знаменитой антиутопии Олдокса Хаксли",
                            "Друзья,druzya,Культовый комедийный телесериал"})
    @ParameterizedTest(name="Поиск различных сериалов: {0}")
    public void searchSerialTest(String serial){
        String[] split = serial.split(",");
        $("button[data-test='header_search']").click();
        $("input[type='text']").click();
        $("input[type='text']").setValue(split[0]).pressEnter();
        $("a[href='/watch/" + split[1]+"']").click();
        $("[data-test='details_text']").$("p").shouldHave(Condition.text(split[2]));

    }
}
