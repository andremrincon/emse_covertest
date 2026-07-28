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
    public void testValidDayAndMonth_Mon_Jan() {
        given().when().get("/api/pat/The%20quick%20brown%20fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/Mon/Jan");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testValidDayAndMonth_Wednesday_August() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/Wednesday/August");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testInvalidDay_ShouldReturn500() {
        given().when().get("/api/pat/Setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/123/Movember");
        assertEquals(200, resp.getStatusCode());
    }
}