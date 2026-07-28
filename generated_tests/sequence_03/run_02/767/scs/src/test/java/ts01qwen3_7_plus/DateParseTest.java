package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testDateParseTueFeb() {
        given()
            .pathParam("dayname", "tue")
            .pathParam("monthname", "feb")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedApr() {
        given()
            .pathParam("dayname", "wed")
            .pathParam("monthname", "apr")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurMay() {
        given()
            .pathParam("dayname", "thur")
            .pathParam("monthname", "may")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriJun() {
        given()
            .pathParam("dayname", "fri")
            .pathParam("monthname", "jun")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSatJul() {
        given()
            .pathParam("dayname", "sat")
            .pathParam("monthname", "jul")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSunAug() {
        given()
            .pathParam("dayname", "sun")
            .pathParam("monthname", "aug")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}