package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Test(timeout = 60000)
    public void testCostfuns_i5_sBaab() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        int i = 5;
        String s = "baab";

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/{i}/{s}", i, s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNeg500_sA() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        int i = -500;
        String s = "a";

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/{i}/{s}", i, s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i700_sAbab() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        int i = 700;
        String s = "abab";

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/{i}/{s}", i, s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNeg4_sAbab() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        int i = -4;
        String s = "abab";

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/{i}/{s}", i, s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i0_sAbabba() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        int i = 0;
        String s = "ababba";

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/{i}/{s}", i, s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i600_sZ() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        int i = 600;
        String s = "z";

        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/costfuns/{i}/{s}", i, s)
        .then()
            .statusCode(200);
    }
}