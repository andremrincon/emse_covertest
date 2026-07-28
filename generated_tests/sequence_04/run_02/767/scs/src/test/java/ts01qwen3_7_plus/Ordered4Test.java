package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "delta")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("w", "delta")
            .pathParam("x", "cherry")
            .pathParam("z", "apple")
            .pathParam("y", "banana")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("w", "apple")
            .pathParam("x", "cherry")
            .pathParam("z", "delta")
            .pathParam("y", "banana")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testInvalidLengthTooShort() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("w", "app")
            .pathParam("x", "banana")
            .pathParam("z", "delta")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testInvalidLengthTooLong() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("w", "appleee")
            .pathParam("x", "banana")
            .pathParam("z", "delta")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}