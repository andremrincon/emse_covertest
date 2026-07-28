package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.server");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_returns10() {
        given().when().get("/api/pat/{txt}", "arrange-1").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/{i}/{s}", 5, "a");
        response.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNegativeLarge_returns10() {
        given().when().get("/api/pat/{txt}", "arrange-2").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/{i}/{s}", -500, "a");
        response.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLarge_returns10() {
        given().when().get("/api/pat/{txt}", "arrange-3").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/{i}/{s}", 700, "a");
        response.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsBaab_branchCovered_returns10() {
        given().when().get("/api/pat/{txt}", "arrange-4").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/{i}/{s}", 0, "baab");
        response.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_sIsAbab_iZero_returns6() {
        given().when().get("/api/pat/{txt}", "arrange-5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/{i}/{s}", 0, "abab");
        response.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus4_sAbab_returns0() {
        given().when().get("/api/pat/{txt}", "arrange-6").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        response.then().statusCode(200).body(equalTo("10"));
    }
}