package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testValidDayWedAndMonthAug() {
        given()
            .when()
                .get("/api/dateparse/wed/aug")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testValidDayTueAndMonthMar() {
        given()
            .when()
                .get("/api/dateparse/tue/MAR")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/Superday/Movember")
            .then()
                .statusCode(200);
    }
}