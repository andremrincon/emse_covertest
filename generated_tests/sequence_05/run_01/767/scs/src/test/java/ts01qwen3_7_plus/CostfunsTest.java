package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNotEqualMinus4() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5AndSEqualsBaab() {
        given()
            .pathParam("i", 5)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444() {
        given()
            .pathParam("i", -445)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessEqualMinus333() {
        given()
            .pathParam("i", -333)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        given()
            .pathParam("i", 667)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToAbabba() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}