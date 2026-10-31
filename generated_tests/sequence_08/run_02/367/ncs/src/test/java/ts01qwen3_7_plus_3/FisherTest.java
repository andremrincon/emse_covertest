package ts01qwen3_7_plus_3;

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
    public void testFisherOddMOddN() {
        given()
            .when()
            .get("/api/fisher/1/1/0.5")
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
    public void testFisherEvenMOddN() {
        given()
            .when()
            .get("/api/fisher/2/1/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        given()
            .when()
            .get("/api/fisher/2/2/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithFirstLoop() {
        given()
            .when()
            .get("/api/fisher/1/5/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithExtremeValues() {
        given()
            .when()
            .get("/api/fisher/10/5/100.0")
            .then()
            .statusCode(200);
    }
}