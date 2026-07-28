package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCostfuns_IEquals5_SequalsBaab() {
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
    public void testCostfuns_ILessThanMinus444_SequalsA() {
        given()
            .pathParam("i", -500)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_IGreaterThan666_SequalsAbabba() {
        given()
            .pathParam("i", 700)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_IEqualsMinus4_SequalsAbab() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_IEquals0_SequalsA() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }
}