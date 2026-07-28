package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_sEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444_sLessThanAbabba() {
        given()
            .when()
                .get("/api/costfuns/-500/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iBetweenMinus444AndMinus333() {
        given()
            .when()
                .get("/api/costfuns/-400/algorithm")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/700/algorithm")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iBetween555And666() {
        given()
            .when()
                .get("/api/costfuns/600/algorithm")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4() {
        given()
            .when()
                .get("/api/costfuns/-4/algorithm")
            .then()
                .statusCode(200);
    }
}