package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_text2txt_two_returns_2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "ignore" + uid, "ignore").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "any", "any").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "any", "any").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "any", "any").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "any").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "nothing");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_text2txt_for_returns_4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "x" + uid, "y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "something", uid);
        resp.then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void test_text2txt_you_returns_u() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "one", "two").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "ignored", "ignored");
        resp.then().assertThat().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void test_text2txt_and_returns_n() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "a", "b").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y");
        resp.then().assertThat().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void test_text2txt_are_returns_r() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "p", "q").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "b", "c");
        resp.then().assertThat().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void test_text2txt_see_you_returns_cu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "m", "n").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later");
        resp.then().assertThat().body(equalTo("cu"));
    }
}