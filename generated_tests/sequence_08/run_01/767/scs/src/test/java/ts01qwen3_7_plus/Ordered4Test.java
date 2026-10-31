package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "doggy")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .pathParam("w", "doggy")
            .pathParam("x", "cherry")
            .pathParam("z", "apple")
            .pathParam("y", "banana")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .pathParam("w", "hello")
            .pathParam("x", "hello")
            .pathParam("z", "hello")
            .pathParam("y", "hello")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidLengths() {
        given()
            .pathParam("w", "first")
            .pathParam("x", "second")
            .pathParam("z", "")
            .pathParam("y", "fourth")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(404);
    }
}