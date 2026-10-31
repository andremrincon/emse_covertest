package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfuns_1() {
        given()
            .when()
                .get("/api/costfuns/5/baba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_2() {
        given()
            .when()
                .get("/api/costfuns/-500/abab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_3() {
        given()
            .when()
                .get("/api/costfuns/700/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_4() {
        given()
            .when()
                .get("/api/costfuns/-4/ababbaa")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_5() {
        given()
            .when()
                .get("/api/costfuns/600/ababbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_6() {
        given()
            .when()
                .get("/api/costfuns/0/ab")
            .then()
                .statusCode(200);
    }
}