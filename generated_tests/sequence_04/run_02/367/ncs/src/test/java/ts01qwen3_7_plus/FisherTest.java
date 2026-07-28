package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisherStandardCase() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherVeryLargeX() {
        given()
            .when()
            .get("/api/fisher/1/1/10000000.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherVerySmallX() {
        given()
            .when()
            .get("/api/fisher/2/2/0.0000001")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeParameters() {
        given()
            .when()
            .get("/api/fisher/50/50/2.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParameterType() {
        given()
            .when()
            .get("/api/fisher/abc/5/0.75")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherZeroXValue() {
        given()
            .when()
            .get("/api/fisher/10/5/0.0")
            .then()
            .statusCode(200);
    }
}