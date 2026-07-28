package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/apple/banana/cherry/dates")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/zebra/yakker/xray/wolf")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLengthConstraint() {
        given()
                .when()
                .get("/api/ordered4/ab/banana/cherry/dates")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToMixedComparison() {
        given()
                .when()
                .get("/api/ordered4/apple/cherry/banana/dates")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToAllLengthsTooLong() {
        given()
                .when()
                .get("/api/ordered4/apples/bananas/cherries/datesxx")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToAllLengthsTooShort() {
        given()
                .when()
                .get("/api/ordered4/a/b/c/d")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}