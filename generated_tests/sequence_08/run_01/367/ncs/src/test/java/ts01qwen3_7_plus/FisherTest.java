package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    private final String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testFisher_a1_b1() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/1/1/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/1/2/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pLessThan0() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/2/2/-0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pGreaterThan1() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/10/5/1e15")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_loops() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/10/5/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidParam() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/abc/5/0.5")
        .then()
            .statusCode(400);
    }
}