package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testFisherValidParams1() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/10/5/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherValidParams2() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/1/1/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParamM() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/abc/5/0.75")
        .then()
            .statusCode(400);
    }
}