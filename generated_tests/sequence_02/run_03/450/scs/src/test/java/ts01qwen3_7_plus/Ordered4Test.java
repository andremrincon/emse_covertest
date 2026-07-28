package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        }
    }

    @Test(timeout = 60000)
    public void testUnorderedShortLengths() {
        given()
            .when()
                .get("/api/ordered4/a/b/c/d")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .when()
                .get("/api/ordered4/bbbbb/aaaaa/ddddd/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedShortCircuit1() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/ccccc/ddddd/bbbbb")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedShortCircuit2() {
        given()
            .when()
                .get("/api/ordered4/ddddd/bbbbb/aaaaa/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}