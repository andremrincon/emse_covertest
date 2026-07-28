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
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevarReturnsZero() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 5, "d");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevarReturnsTwo() {
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 5, "z");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevarReturnsThree() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 6, "a");
        assertEquals("3", act.asString());
    }
}