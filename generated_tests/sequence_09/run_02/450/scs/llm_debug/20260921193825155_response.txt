package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("API_BASE", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "ignored", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "x", "y").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testForReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", UUID.randomUUID().toString(), "val").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYouReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "anything", "here").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAreReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "something", "else").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouReturns200() throws Exception {
        String encoded = URLEncoder.encode("The quick brown fox", "UTF-8").replace("+", "%20");
        given().when().get("/api/pat/{txt}", encoded).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(200);
    }
}