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
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5() {
        int i = 5;
        String s = "test";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        int i = 667;
        String s = "test";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNotEqualsMinus4() {
        int i = 0;
        String s = "test";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsBaab() {
        int i = 0;
        String s = "baab";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToGreaterThan0() {
        int i = 0;
        String s = "z";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToGreaterThanOrEquals0() {
        int i = 0;
        String s = "ababba";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}