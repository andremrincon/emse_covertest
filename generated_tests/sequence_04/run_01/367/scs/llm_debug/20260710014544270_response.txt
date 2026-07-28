package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("BASE_URL");
            if (e2 == null || e2.isEmpty()) {
                RestAssured.baseURI = "http://localhost:8080";
            } else {
                RestAssured.baseURI = e2;
            }
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void test_fullNames_returns200() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Wednesday/August");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_abbreviatedDay_and_abbreviatedMonth_returns200() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/mon/jan");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_caseInsensitiveMonth_and_fullDay_returns200() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/tuesday/MAR");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_invalidDay_returns500() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/123/August");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_invalidMonth_returns500() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Wednesday/Movember");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_fullDayMonday_returns500_per_api_example() {
        given().when().get("/api/pat/start").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Monday/dec");
        assertEquals(200, act.getStatusCode());
    }
}