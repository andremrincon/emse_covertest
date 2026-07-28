package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_compareToLessThan_setsResult2() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 0, "z");
        assertEquals("2", act.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void test_allConditionsFalse_returnsZero() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 5, "a");
        assertEquals("0", act.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void test_yGreaterThanFive_overwritesWithThree_i6() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 6, "a");
        assertEquals("3", act.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void test_hello7Concatenation_executed_returnsThree_dueToLaterBranch_i7() {
        given().when().get("/api/pat/{txt}", "setup2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals("3", act.getBody().asString().replace("\"", ""));
    }

    @Test(timeout = 60000)
    public void test_doubleEqualsFiftySix_branchExecuted_i28_returnsThree() {
        given().when().get("/api/pat/{txt}", "setup3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        assertEquals("3", act.getBody().asString().replace("\"", ""));
    }
}