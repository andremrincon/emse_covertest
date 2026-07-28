package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeX() {
        given()
            .when()
                .get("/api/fisher/10/5/1000000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherSmallX() {
        given()
            .when()
                .get("/api/fisher/10/5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        given()
            .when()
                .get("/api/fisher/1/4/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
            .when()
                .get("/api/fisher/abc/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        given()
            .when()
                .get("/api/fisher/4/4/0.001")
            .then()
                .statusCode(200);
    }
}