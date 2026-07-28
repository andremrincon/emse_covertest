package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfunsBranch1() {
        int i = 5;
        String s = "z";
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/costfuns/" + i + "/" + s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranch2() {
        int i = -445;
        String s = "a";
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/costfuns/" + i + "/" + s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranch3() {
        int i = 667;
        String s = "baab";
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/costfuns/" + i + "/" + s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranch4() {
        int i = -4;
        String s = "abab";
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/costfuns/" + i + "/" + s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranch5() {
        int i = 555;
        String s = "ababba";
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/costfuns/" + i + "/" + s)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranch6() {
        int i = -333;
        String s = "x";
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/costfuns/" + i + "/" + s)
        .then()
            .statusCode(200);
    }
}