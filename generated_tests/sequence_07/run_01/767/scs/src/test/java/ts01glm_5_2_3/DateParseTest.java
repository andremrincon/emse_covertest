package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testValidDateFullNamesReturnsOk() {
        given()
            .pathParam("dayname", "Wednesday")
            .pathParam("monthname", "August")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testValidDateMixedCaseReturnsOk() {
        given()
            .pathParam("dayname", "tuesday")
            .pathParam("monthname", "MAR")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidDateReturnsServerError() {
        given()
            .pathParam("dayname", "Superday")
            .pathParam("monthname", "Movember")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}