package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jan")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
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
    public void testDateParseWedMar() {
        given()
            .pathParam("dayname", "wed")
            .pathParam("monthname", "mar")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurApr() {
        given()
            .pathParam("dayname", "thur")
            .pathParam("monthname", "apr")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriMay() {
        given()
            .pathParam("dayname", "fri")
            .pathParam("monthname", "may")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalid() {
        given()
            .pathParam("dayname", "xyz")
            .pathParam("monthname", "xyz")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}