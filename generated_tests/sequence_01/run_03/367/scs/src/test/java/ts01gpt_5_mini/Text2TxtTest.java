package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "ignore", "ignore").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordFourReturns4() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "quick", "brown").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreTakesPrecedenceAndReturnsR() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "Are", "You", "anything").then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyString() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "x").then().body(equalTo(""));
    }
}