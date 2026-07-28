package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "else").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "ignored", "ignored");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "something", "else");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "there", "friend").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "a", "b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "will", "see");
        assertEquals("u", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "x", "y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "we", "there");
        assertEquals("r", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "a", "b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", UUID.randomUUID().toString());
        assertEquals("cu", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyString() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "x", "y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "helloWorld", "alpha", "beta");
        assertEquals("", act.getBody().asString());
    }
}