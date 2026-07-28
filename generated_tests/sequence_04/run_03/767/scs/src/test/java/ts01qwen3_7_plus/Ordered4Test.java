package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidLengthsReturnsUnordered() {
        given()
            .when()
                .get("/api/ordered4/a/b/c/d")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsButUnorderedReturnsUnordered() {
        given()
            .when()
                .get("/api/ordered4/apple/apple/apple/apple")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsIncreasingReturnsIncreasing() {
        given()
            .when()
                .get("/api/ordered4/apple/banana/donut/cherry")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsDecreasingReturnsDecreasing() {
        given()
            .when()
                .get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx")
            .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testFirstLengthValidSecondInvalidReturnsUnordered() {
        given()
            .when()
                .get("/api/ordered4/apple/b/c/d")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}