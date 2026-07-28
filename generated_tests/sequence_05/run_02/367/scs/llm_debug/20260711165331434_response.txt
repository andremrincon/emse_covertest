package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_IEquals5() {
        RestAssured.baseURI = getBaseUrl();
        int i = 5;
        String s = "a";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_ILessThanMinus444() {
        RestAssured.baseURI = getBaseUrl();
        int i = -500;
        String s = "a";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_IGreaterThan666() {
        RestAssured.baseURI = getBaseUrl();
        int i = 700;
        String s = "a";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_INotEqualsMinus4() {
        RestAssured.baseURI = getBaseUrl();
        int i = -4;
        String s = "a";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_SEqualsBaab() {
        RestAssured.baseURI = getBaseUrl();
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
    public void testCostfuns_SNotEqualsAbab() {
        RestAssured.baseURI = getBaseUrl();
        int i = 0;
        String s = "abab";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}