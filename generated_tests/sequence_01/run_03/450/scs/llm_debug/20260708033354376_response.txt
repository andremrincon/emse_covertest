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
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testWLengthTooShort() {
        given()
            .pathParam("w", "aaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ddddd")
            .pathParam("y", "ccccc")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testWLengthTooLong() {
        given()
            .pathParam("w", "aaaaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ddddd")
            .pathParam("y", "ccccc")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testXLengthTooShort() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbb")
            .pathParam("z", "ddddd")
            .pathParam("y", "ccccc")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsUnordered() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "ccccc")
            .pathParam("z", "ddddd")
            .pathParam("y", "bbbbb")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsIncreasing() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ddddd")
            .pathParam("y", "ccccc")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsDecreasing() {
        given()
            .pathParam("w", "ddddd")
            .pathParam("x", "ccccc")
            .pathParam("z", "aaaaa")
            .pathParam("y", "bbbbb")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }
}