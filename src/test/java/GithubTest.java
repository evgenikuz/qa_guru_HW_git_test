import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubTest {
    @BeforeAll()
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";

    }

    @Test
    void shouldHaveExampleCodeInSoftAssertions() {
        // открываем страницу селенида
        open("/selenide/selenide");
        // клик по wiki
        $("#wiki-tab").click();

        // фильтрация по страницам справа
        $("#wiki-pages-filter").setValue("soft");
        //проверка, что SoftAssertions в списке страниц и видима, т.е. отфильтровалась
        $("[data-filterable-for=wiki-pages-filter]").shouldHave(text("SoftAssertions")).shouldBe(visible);
        //переход на страницу SoftAssertions
        $("[data-filterable-for=wiki-pages-filter]").$(byText("SoftAssertions")).click();
        // проверка, что есть заголовок с примером кода на JUnite5
        $("#user-content-3-using-junit5-extend-test-class").preceding(0).shouldHave(text("3. Using JUnit5 extend test class:"));
    }
}
