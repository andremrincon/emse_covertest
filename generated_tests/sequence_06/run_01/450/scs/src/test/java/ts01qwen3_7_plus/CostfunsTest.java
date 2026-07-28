package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_i5_baab() {
        given()
            .when()
            .get("/api/costfuns/5/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNeg500_a() {
        given()
            .when()
            .get("/api/costfuns/-500/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNeg4_ababba() {
        given()
            .when()
            .get("/api/costfuns/-4/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i600_z() {
        given()
            .when()
            .get("/api/costfuns/600/z")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i700_x() {
        given()
            .when()
            .get("/api/costfuns/700/x")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i0_abab() {
        given()
            .when()
            .get("/api/costfuns/0/abab")
            .then()
            .statusCode(200);
    }
}