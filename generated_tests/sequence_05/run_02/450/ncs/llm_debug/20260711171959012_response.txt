package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBessjSuccess() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNLow() {
        given()
            .when()
                .get("/api/bessj/2/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNHigh() {
        given()
            .when()
                .get("/api/bessj/1001/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
            .when()
                .get("/api/fisher/1001/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidN() {
        given()
            .when()
                .get("/api/fisher/10/1001/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeException() {
        given()
            .when()
                .get("/api/fisher/10/5/1.2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccess() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfBounds() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }
}