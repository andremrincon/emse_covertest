package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DateParseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDateParseSatMar() {
        given()
            .when()
            .get("/api/dateparse/sat/mar")
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
    public void testDateParseSunJul() {
        given()
            .when()
            .get("/api/dateparse/sun/jul")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseMonSep() {
        given()
            .when()
            .get("/api/dateparse/mon/sep")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseTueOct() {
        given()
            .when()
            .get("/api/dateparse/tue/oct")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedDec() {
        given()
            .when()
            .get("/api/dateparse/wed/dec")
            .then()
            .statusCode(200);
    }
}