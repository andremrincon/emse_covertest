package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "cherry")
            .pathParam("y", "date")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .pathParam("w", "zebra")
            .pathParam("x", "yak")
            .pathParam("z", "vulture")
            .pathParam("y", "wolf")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "cherry")
            .pathParam("y", "elder")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithInvalidLengths() {
        given()
            .pathParam("w", "app")
            .pathParam("x", "banana")
            .pathParam("z", "cherry")
            .pathParam("y", "elder")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithTooLongStrings() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "cherry")
            .pathParam("y", "elderberry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testBoundaryLengths() {
        given()
            .pathParam("w", "abcde")
            .pathParam("x", "fghij")
            .pathParam("z", "klmno")
            .pathParam("y", "pqrst")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}