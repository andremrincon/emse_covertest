package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5() {
        given()
            .pathParam("i", 5)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        given()
            .pathParam("i", 667)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444() {
        given()
            .pathParam("i", -445)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4_sEqualsBaab() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsAbab() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsAbabba() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }
}