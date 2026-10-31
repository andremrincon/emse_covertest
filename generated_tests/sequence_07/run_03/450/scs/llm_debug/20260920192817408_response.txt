package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthCheckFails() {
        given()
        .when()
            .get("/api/ordered4/a/bbbbb/ccccc/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
        .when()
            .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
        .when()
            .get("/api/ordered4/ddddd/ccccc/bbbbb/aaaaa")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthPassesButNeitherOrder() {
        given()
        .when()
            .get("/api/ordered4/aaaaa/ccccc/bbbbb/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderWithLengthSix() {
        given()
        .when()
            .get("/api/ordered4/aaaaaa/bbbbbb/cccccc/dddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderWithLengthSix() {
        given()
        .when()
            .get("/api/ordered4/dddddd/cccccc/bbbbbb/aaaaaa")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}