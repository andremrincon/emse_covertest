package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(lessThan(300))
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(lessThan(300))
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooShort() {
        given()
            .when()
                .get("/api/ordered4/a/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(lessThan(300))
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooLong() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(lessThan(300))
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengthNeitherIncreasingNorDecreasing() {
        given()
            .when()
                .get("/api/ordered4/bbbbb/aaaaa/ddddd/ccccc")
            .then()
                .statusCode(lessThan(300))
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testServerErrorWithVeryLongString() {
        String longString = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/" + longString)
            .then()
                .statusCode(200);
    }
}