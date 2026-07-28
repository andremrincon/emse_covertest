package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_sEqualsBaab() {
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
    public void testCostfuns_iLessThanMinus444_sLessThanAbabba() {
        given()
            .pathParam("i", -500)
            .pathParam("s", "aaaa")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_sGreaterThanAbabba() {
        given()
            .pathParam("i", 700)
            .pathParam("s", "test")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4_sEqualsAbabba() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan555_sGreaterThanAbabba() {
        given()
            .pathParam("i", 600)
            .pathParam("s", "algorithm")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iZero_sLessThanAbabba() {
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