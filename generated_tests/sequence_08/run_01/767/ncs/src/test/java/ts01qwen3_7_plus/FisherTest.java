package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class FisherTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherOddOdd() {
        RestAssured.given()
            .when()
            .get("/api/fisher/1/1/1.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddEven() {
        RestAssured.given()
            .when()
            .get("/api/fisher/1/2/1.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenOdd() {
        RestAssured.given()
            .when()
            .get("/api/fisher/2/1/1.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenEven() {
        RestAssured.given()
            .when()
            .get("/api/fisher/2/2/1.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherSwaggerExample() {
        RestAssured.given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherAdditionalCase() {
        RestAssured.given()
            .when()
            .get("/api/fisher/3/4/2.5")
            .then()
            .statusCode(200);
    }
}