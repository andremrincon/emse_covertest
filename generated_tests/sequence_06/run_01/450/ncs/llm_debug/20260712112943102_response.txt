package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherNormal() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
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
    public void testFisherExtremeM() {
        given()
            .when()
                .get("/api/fisher/100/1/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherExtremeX() {
        given()
            .when()
                .get("/api/fisher/1/1/0.9999999999999999")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
            .when()
                .get("/api/fisher/abc/5/0.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidX() {
        given()
            .when()
                .get("/api/fisher/10/5/1.2")
            .then()
                .statusCode(200);
    }
}