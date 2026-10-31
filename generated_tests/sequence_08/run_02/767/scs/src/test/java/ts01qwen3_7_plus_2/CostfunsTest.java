package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfunsCase1() {
        given()
            .pathParam("i", 5)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsCase2() {
        given()
            .pathParam("i", -400)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsCase3() {
        given()
            .pathParam("i", -500)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsCase4() {
        given()
            .pathParam("i", 600)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsCase5() {
        given()
            .pathParam("i", 700)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsCase6() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }
}