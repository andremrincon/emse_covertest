package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario1() {
        RestAssured.given()
                .when()
                .get("/api/costfuns/5/baab")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario2() {
        RestAssured.given()
                .when()
                .get("/api/costfuns/-500/z")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario3() {
        RestAssured.given()
                .when()
                .get("/api/costfuns/-400/ababba")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario4() {
        RestAssured.given()
                .when()
                .get("/api/costfuns/700/x")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario5() {
        RestAssured.given()
                .when()
                .get("/api/costfuns/600/abab")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario6() {
        RestAssured.given()
                .when()
                .get("/api/costfuns/-4/y")
                .then()
                .statusCode(200);
    }
}