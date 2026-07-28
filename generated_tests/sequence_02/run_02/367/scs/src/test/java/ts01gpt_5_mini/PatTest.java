package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void init() {
        String envBase = System.getProperty("api.base");
        if (envBase == null || envBase.isEmpty()) {
            String env = System.getenv("API_BASE");
            envBase = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = envBase;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testShortPatternReturns200() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        String txt = "helloWorld";
        String pat = "ab";
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatThenReverseAdjacentReturnsIndex() {
        String setupTxt = "ready-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        String txt = "xxABCCBAyy";
        String pat = "ABC";
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacentReturnsIndex() {
        String setupTxt = "ready2-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        String txt = "zzCBAABCzz";
        String pat = "ABC";
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatFoundWithoutReverseReturnsOne() {
        String setupTxt = "ready3-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        String txt = "xxABCyy";
        String pat = "ABC";
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseOnlyReturnsTwo() {
        String setupTxt = "ready4-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        String txt = "xxCBAyy";
        String pat = "ABC";
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("2"));
    }
}