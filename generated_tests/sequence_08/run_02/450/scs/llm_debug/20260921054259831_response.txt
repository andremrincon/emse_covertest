package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testLengthsInvalid() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/a/bbbbb/ddddd/ccccc")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
        .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/eeeee/ddddd/bbbbb/ccccc")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedZLessThanY() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/aaaaa/bbbbb/bbbbb/ccccc")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedYLessThanX() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/aaaaa/ccccc/ddddd/bbbbb")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedXLessThanY() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/ccccc/bbbbb/aaaaa/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}