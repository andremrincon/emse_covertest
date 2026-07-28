package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class Ordered4Test {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUnorderedInvalidLength() {
        String baseUrl = getBaseUrl();
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/ordered4/a/b/c/d")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLength() {
        String baseUrl = getBaseUrl();
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/ordered4/apple/apple/apple/apple")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        String baseUrl = getBaseUrl();
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/ordered4/apple/berry/dates/cherry")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        String baseUrl = getBaseUrl();
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/ordered4/zebra/yakkk/wolfy/xrayy")
                .then()
                .statusCode(200);
    }
}