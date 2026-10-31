package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonth() {
        given()
                .when()
                .get("/api/dateparse/Wednesday/August")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndInvalidMonth() {
        given()
                .when()
                .get("/api/dateparse/Superday/Movember")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayInvalidMonth() {
        given()
                .when()
                .get("/api/dateparse/tuesday/456")
                .then()
                .statusCode(200);
    }
}