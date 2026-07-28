package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherOddOdd() {
        given()
            .when()
            .get("/api/fisher/1/1/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEven() {
        given()
            .when()
            .get("/api/fisher/1/4/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOdd() {
        given()
            .when()
            .get("/api/fisher/4/1/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenEven() {
        given()
            .when()
            .get("/api/fisher/4/4/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherZeroX() {
        given()
            .when()
            .get("/api/fisher/10/5/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherNormalX() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }
}