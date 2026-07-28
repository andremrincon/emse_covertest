package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "berry")
            .pathParam("z", "dates")
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
            .pathParam("w", "zebra")
            .pathParam("x", "yacht")
            .pathParam("z", "wheat")
            .pathParam("y", "xerox")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testLengthTooShort() {
        given()
            .pathParam("w", "app")
            .pathParam("x", "berry")
            .pathParam("z", "dates")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthTooLong() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "berry")
            .pathParam("z", "datefruit")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsButUnordered() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "berry")
            .pathParam("z", "apple")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}