package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherOddOdd() {
        RestAssured.given()
            .when()
            .get("/api/fisher/1/1/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEven() {
        RestAssured.given()
            .when()
            .get("/api/fisher/1/2/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOdd() {
        RestAssured.given()
            .when()
            .get("/api/fisher/2/1/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenEven() {
        RestAssured.given()
            .when()
            .get("/api/fisher/2/2/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherSmallX() {
        RestAssured.given()
            .when()
            .get("/api/fisher/3/3/1e-10")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeX() {
        RestAssured.given()
            .when()
            .get("/api/fisher/3/3/1e10")
            .then()
            .statusCode(200);
    }
}