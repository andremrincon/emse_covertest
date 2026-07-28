package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_withAbab_returns200() {
        given().when().get("/api/pat/{txt}", "setup1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444_returns200() {
        given().when().get("/api/pat/{txt}", "setup2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_returns200() {
        given().when().get("/api/pat/{txt}", "setup3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4_withBaab_exercisesStringEquals_returns200() {
        given().when().get("/api/pat/{txt}", "setup4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareToEqualsZero_withAbabba_returns200() {
        given().when().get("/api/pat/{txt}", "setup5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "ababba");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sNotEqualAbab_returns200() {
        given().when().get("/api/pat/{txt}", "setup6").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "randomstring");
        resp.then().statusCode(200);
    }
}