package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5() {
        String baseUrl = getBaseUrl();
        given()
            .when()
            .get(baseUrl + "/api/costfuns/5/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        String baseUrl = getBaseUrl();
        given()
            .when()
            .get(baseUrl + "/api/costfuns/667/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iNotEqualsMinus4() {
        String baseUrl = getBaseUrl();
        given()
            .when()
            .get(baseUrl + "/api/costfuns/0/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsBaab() {
        String baseUrl = getBaseUrl();
        given()
            .when()
            .get(baseUrl + "/api/costfuns/0/baab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToAbabbaGreaterOrEqual() {
        String baseUrl = getBaseUrl();
        given()
            .when()
            .get(baseUrl + "/api/costfuns/0/ababba")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToAbabbaGreater() {
        String baseUrl = getBaseUrl();
        given()
            .when()
            .get(baseUrl + "/api/costfuns/0/z")
            .then()
            .statusCode(200);
    }
}