package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherMOddNOdd() {
        given()
            .when()
            .get("/api/fisher/1/1/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEven() {
        given()
            .when()
            .get("/api/fisher/1/2/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPLessThanZero() {
        given()
            .when()
            .get("/api/fisher/1/1/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThanOne() {
        given()
            .when()
            .get("/api/fisher/10/5/100.0")
            .then()
            .statusCode(200);
    }
}