package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testCostfuns_i5_sZ() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/5/z")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i667_sZ() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/667/z")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i0_sBaab() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/0/baab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i0_sA() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/0/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i0_sAbab() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/0/abab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus4_sA() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/-4/a")
        .then()
            .statusCode(200);
    }
}