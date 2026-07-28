package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
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
    public void testBessjError() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
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
    public void testFisherErrorM() {
        given()
            .when()
                .get("/api/fisher/1001/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherErrorN() {
        given()
            .when()
                .get("/api/fisher/10/1001/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqSuccess() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqError() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.3")
            .then()
                .statusCode(400);
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
    public void testRemainderErrorA() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderErrorB() {
        given()
            .when()
                .get("/api/remainder/17/10001")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderErrorMinusA() {
        given()
            .when()
                .get("/api/remainder/-10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderErrorMinusB() {
        given()
            .when()
                .get("/api/remainder/17/-10001")
            .then()
                .statusCode(400);
    }
}