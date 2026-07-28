package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testUrlPattern() {
        String seed = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", "http://abc/def");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testDatePattern() {
        String seed = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", "mon12jan");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePattern() {
        String seed = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", "1.2e+34");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePattern() {
        String seed = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", "foobar");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatWithPattern() {
        String seed = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        act.then().statusCode(200);
    }
}