package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class CostfunsTest {

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/costfuns/{i}/{s}", 5, "a")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/costfuns/{i}/{s}", -500, "a")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/costfuns/{i}/{s}", 700, "a")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/costfuns/{i}/{s}", -4, "a")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsBaab() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/costfuns/{i}/{s}", 0, "baab")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsAbabba() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/costfuns/{i}/{s}", 0, "ababba")
                .then()
                .statusCode(200);
    }
}