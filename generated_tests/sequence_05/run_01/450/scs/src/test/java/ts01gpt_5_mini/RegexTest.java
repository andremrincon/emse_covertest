package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternResultsIn200() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String id2 = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String txt = "http://" + id + "/" + id2;
        Response act = given().pathParam("txt", txt).when().get("/api/pat/{txt}");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testDatePatternResultsIn200() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        Response act = given().pathParam("txt", txt).when().get("/api/pat/{txt}");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingExpPatternResultsIn200() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "12.34e+05";
        Response act = given().pathParam("txt", txt).when().get("/api/pat/{txt}");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternResultsIn200() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "helloWorld123";
        Response act = given().pathParam("txt", txt).when().get("/api/pat/{txt}");
        act.then().statusCode(200);
    }
}