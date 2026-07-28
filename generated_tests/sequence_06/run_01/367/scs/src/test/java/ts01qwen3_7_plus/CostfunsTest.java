package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_sEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/5/baab")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444() {
        given()
            .when()
                .get("/api/costfuns/-500/xyz")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_sEqualsAbabba() {
        given()
            .when()
                .get("/api/costfuns/700/ababba")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4() {
        given()
            .when()
                .get("/api/costfuns/-4/test")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToAbabbaEqualsZero() {
        given()
            .when()
                .get("/api/costfuns/0/ababba")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToAbabbaLessThanZero() {
        given()
            .when()
                .get("/api/costfuns/0/ababb")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }
}