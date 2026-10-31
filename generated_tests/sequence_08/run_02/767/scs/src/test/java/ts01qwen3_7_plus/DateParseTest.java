package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
    public void testDateParse_Mon_Jan() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Tue_Feb() {
        given()
            .when()
                .get("/api/dateparse/tue/feb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Wed_Mar() {
        given()
            .when()
                .get("/api/dateparse/wed/mar")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Thur_Apr() {
        given()
            .when()
                .get("/api/dateparse/thur/apr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Fri_May() {
        given()
            .when()
                .get("/api/dateparse/fri/may")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParse_Sun_Dec() {
        given()
            .when()
                .get("/api/dateparse/sun/dec")
            .then()
                .statusCode(200);
    }
}