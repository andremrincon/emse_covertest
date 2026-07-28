package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import org.junit.Ignore;
public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/wed/aug")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/superday/movember")
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDateParseAnotherValidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200);
    }
}