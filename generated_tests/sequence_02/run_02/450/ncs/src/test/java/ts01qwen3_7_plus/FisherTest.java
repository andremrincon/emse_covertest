package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisherEvenMOddN() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenN() {
        given()
            .when()
            .get("/api/fisher/1/2/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddN() {
        given()
            .when()
            .get("/api/fisher/1/1/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMEvenN() {
        given()
            .when()
            .get("/api/fisher/2/2/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherZeroX() {
        given()
            .when()
            .get("/api/fisher/1/1/0.0")
            .then()
            .statusCode(200);
    }
}