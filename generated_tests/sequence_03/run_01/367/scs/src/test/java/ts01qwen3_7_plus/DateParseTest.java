package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DateParseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testWedWithJan() {
        given()
            .when()
                .get("/api/dateparse/wed/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testThurWithFeb() {
        given()
            .when()
                .get("/api/dateparse/thur/feb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSunWithMar() {
        given()
            .when()
                .get("/api/dateparse/sun/mar")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMonWithApr() {
        given()
            .when()
                .get("/api/dateparse/mon/apr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTueWithMay() {
        given()
            .when()
                .get("/api/dateparse/tue/may")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFriWithJun() {
        given()
            .when()
                .get("/api/dateparse/fri/jun")
            .then()
                .statusCode(200);
    }
}