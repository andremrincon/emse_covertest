package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario1() {
        int i = 5;
        String s = "baab";

        given()
            .baseUri(getBaseUrl())
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario2() {
        int i = -500;
        String s = "ababba";

        given()
            .baseUri(getBaseUrl())
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario3() {
        int i = -400;
        String s = "abab";

        given()
            .baseUri(getBaseUrl())
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario4() {
        int i = 700;
        String s = "abaa";

        given()
            .baseUri(getBaseUrl())
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario5() {
        int i = 600;
        String s = "abab";

        given()
            .baseUri(getBaseUrl())
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsScenario6() {
        int i = -4;
        String s = "abab";

        given()
            .baseUri(getBaseUrl())
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}