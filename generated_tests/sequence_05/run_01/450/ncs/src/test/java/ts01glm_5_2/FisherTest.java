package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherAOddBOdd() {
        RestAssured.given()
            .when()
                .get("/api/fisher/1/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherAOddBEven() {
        RestAssured.given()
            .when()
                .get("/api/fisher/1/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherAEvenBOdd() {
        RestAssured.given()
            .when()
                .get("/api/fisher/2/1/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherAEvenBEven() {
        RestAssured.given()
            .when()
                .get("/api/fisher/2/2/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithLoops() {
        RestAssured.given()
            .when()
                .get("/api/fisher/11/7/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidInput() {
        RestAssured.given()
            .when()
                .get("/api/fisher/abc/5/0.75")
            .then()
                .statusCode(400);
    }
}