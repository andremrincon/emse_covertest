package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParse_Monday_January() {
        given()
            .pathParam("dayname", "Monday")
            .pathParam("monthname", "January")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Tuesday_February() {
        given()
            .pathParam("dayname", "Tuesday")
            .pathParam("monthname", "February")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Wednesday_March() {
        given()
            .pathParam("dayname", "Wednesday")
            .pathParam("monthname", "March")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Thursday_April() {
        given()
            .pathParam("dayname", "Thursday")
            .pathParam("monthname", "April")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Friday_May() {
        given()
            .pathParam("dayname", "Friday")
            .pathParam("monthname", "May")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_InvalidDay_InvalidMonth() {
        given()
            .pathParam("dayname", "Superday")
            .pathParam("monthname", "Movember")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}