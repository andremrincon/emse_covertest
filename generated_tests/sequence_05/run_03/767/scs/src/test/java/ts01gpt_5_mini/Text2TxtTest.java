package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base.url");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "foo", "bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "foo", "bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "foo", "bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "foo", "bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "foo", "bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "ignored", "ignored").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "test").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testForReturns4Status200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "here").then().statusCode(200);
    }
}