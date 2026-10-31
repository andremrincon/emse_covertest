package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/apple/banana/datee/cherry")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/zebra/yakkk/wolfy/xrayy")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWTooShort() {
        given()
            .when()
                .get("/api/ordered4/app/banana/datee/cherry")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWTooLong() {
        given()
            .when()
                .get("/api/ordered4/appleee/banana/datee/cherry")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedZEqualsY() {
        given()
            .when()
                .get("/api/ordered4/apple/banana/cherry/cherry")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedYLessThanX() {
        given()
            .when()
                .get("/api/ordered4/apple/cherry/datee/banana")
            .then()
                .statusCode(200);
    }
}