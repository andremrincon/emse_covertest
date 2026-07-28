package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCostfunsIEquals5() {
        given()
            .when()
                .get("/api/costfuns/5/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsILessThanNegative444AndSEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/-500/baab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsIEqualsNegative4() {
        given()
            .when()
                .get("/api/costfuns/-4/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsIGreaterThan666() {
        given()
            .when()
                .get("/api/costfuns/700/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsSEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/600/ababba")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsInvalidInteger() {
        given()
            .when()
                .get("/api/costfuns/one/test")
            .then()
                .statusCode(400);
    }
}