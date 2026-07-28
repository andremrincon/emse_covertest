package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/apple/banana/dates/cherry")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/zebra/yaks!/wolves/x-ray")
            .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .when()
                .get("/api/ordered4/apple/cherry/dates/banana")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testInvalidLengths() {
        given()
            .when()
                .get("/api/ordered4/app/banana/dates/cherry")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}