package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfuns_i5_sBaab() {
        given()
            .when()
            .get("/api/costfuns/5/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus500_sA() {
        given()
            .when()
            .get("/api/costfuns/-500/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus400_sAbabba() {
        given()
            .when()
            .get("/api/costfuns/-400/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i700_sAbab() {
        given()
            .when()
            .get("/api/costfuns/700/abab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_i600_sB() {
        given()
            .when()
            .get("/api/costfuns/600/b")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iMinus4_sC() {
        given()
            .when()
            .get("/api/costfuns/-4/c")
            .then()
            .statusCode(200);
    }
}