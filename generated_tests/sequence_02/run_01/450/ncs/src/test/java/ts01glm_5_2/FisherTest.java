package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddNWithLoops() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddNWithLoop() {
        given()
            .when()
                .get("/api/fisher/3/3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        given()
            .when()
                .get("/api/fisher/1/2/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherNegativeXReturnsZero() {
        given()
            .when()
                .get("/api/fisher/2/2/-0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherNegativeXReturnsOne() {
        given()
            .when()
                .get("/api/fisher/4/2/-1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParameter() {
        given()
            .when()
                .get("/api/fisher/abc/5/0.75")
            .then()
                .statusCode(400);
    }
}