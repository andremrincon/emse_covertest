package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
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
    public void testDateParseWedMar() {
        given()
            .when()
                .get("/api/dateparse/wed/mar")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurApr() {
        given()
            .when()
                .get("/api/dateparse/thur/apr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriMay() {
        given()
            .when()
                .get("/api/dateparse/fri/may")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseSatJun() {
        given()
            .when()
                .get("/api/dateparse/sat/jun")
            .then()
                .statusCode(200);
    }
}