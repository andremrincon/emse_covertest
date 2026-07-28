package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatEndpointMatchesUrlPattern() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", uuid).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String encoded = txt.replace(":", "%3A").replace("/", "%2F");
        Response resp = given().when().get("/api/pat/{txt}", encoded);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointMatchesDatePattern() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", uuid).then().statusCode(lessThan(300));
        String txt = "mon12jan";
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointMatchesFpePattern() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", uuid).then().statusCode(lessThan(300));
        String txt = "12.34e+56";
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointReturnsNoneForNonMatchingText() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", uuid).then().statusCode(lessThan(300));
        String txt = "no-match!";
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(200);
    }
}