package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndValidMonth() {
        given()
                .when()
                .get("/api/dateparse/Wednesday/August")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndInvalidMonth() {
        given()
                .when()
                .get("/api/dateparse/123/456")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndInvalidMonth() {
        given()
                .when()
                .get("/api/dateparse/tuesday/Movember")
                .then()
                .statusCode(200);
    }
}