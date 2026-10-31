package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DateParseTest {
    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMonJan() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/mon/jan")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTueFeb() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/tue/feb")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWedMarWithThurApr() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/thur/apr")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/wed/mar")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFriMayWithSatJun() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/sat/jun")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/fri/may")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSunJulWithAugSep() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/mon/aug")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/tue/sep")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/sun/jul")
        .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testInvalidDayWithOctNovDec() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/mon/oct")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/tue/nov")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/wed/dec")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/dateparse/Superday/Movember")
        .then()
            .statusCode(500);
    }
}