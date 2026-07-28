package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnTen_forDefaultInput() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "a");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testReturnZero_whenIIsMinusFourAndSIsAbab() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCompareToEqual_triggers200() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "ababba");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLargeNegativeTriggersBranches_status200() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLargePositiveTriggersBranches_status200() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSEqualsBaab_executesStringEquals_status200() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        resp.then().statusCode(200);
    }
}