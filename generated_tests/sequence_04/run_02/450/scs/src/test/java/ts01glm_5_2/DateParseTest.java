package ts01glm_5_2;

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
    public void testDateParseSunJan() {
        given()
            .when()
                .get("/api/dateparse/sun/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseMonMar() {
        given()
            .when()
                .get("/api/dateparse/mon/mar")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseTueMay() {
        given()
            .when()
                .get("/api/dateparse/tue/may")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedJul() {
        given()
            .when()
                .get("/api/dateparse/wed/jul")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurAug() {
        given()
            .when()
                .get("/api/dateparse/thur/aug")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriDec() {
        given()
            .when()
                .get("/api/dateparse/fri/dec")
            .then()
                .statusCode(200);
    }
}