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
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUnorderedByLength() {
        given()
            .when()
                .get("/api/ordered4/a/b/c/d")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedByComparison() {
        given()
            .when()
                .get("/api/ordered4/apple/apple/apple/apple")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/bbbbb/aaaaa")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}