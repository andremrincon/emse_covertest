package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisherOddOdd() {
        given()
            .when()
            .get("/api/fisher/1/3/0.75")
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
            .get("/api/fisher/4/2/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeM() {
        given()
            .when()
            .get("/api/fisher/10/3/1.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeN() {
        given()
            .when()
            .get("/api/fisher/4/10/1.0")
            .then()
            .statusCode(200);
    }
}