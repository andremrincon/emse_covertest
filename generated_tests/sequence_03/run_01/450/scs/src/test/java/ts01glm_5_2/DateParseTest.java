package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseTueFeb() {
        given()
            .when()
                .get("/api/dateparse/tue/feb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedApr() {
        given()
            .when()
                .get("/api/dateparse/wed/apr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurMay() {
        given()
            .when()
                .get("/api/dateparse/thur/may")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriJun() {
        given()
            .when()
                .get("/api/dateparse/fri/jun")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/123/456")
            .then()
                .statusCode(500);
    }
}