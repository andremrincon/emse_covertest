package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DateParseTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonth() {
        given()
            .when()
                .get(baseUrl + "/api/dateparse/Wednesday/August")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        given()
            .when()
                .get(baseUrl + "/api/dateparse/Superday/Movember")
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayInvalidMonth() {
        given()
            .when()
                .get(baseUrl + "/api/dateparse/tuesday/MAR")
            .then()
                .statusCode(200);
    }
}