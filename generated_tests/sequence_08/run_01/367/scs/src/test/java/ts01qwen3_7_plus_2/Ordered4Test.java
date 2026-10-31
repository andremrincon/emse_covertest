package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testUnorderedWTooShort() {
        given()
            .pathParam("w", "a")
            .pathParam("x", "b")
            .pathParam("z", "c")
            .pathParam("y", "d")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWTooLong() {
        given()
            .pathParam("w", "apples!")
            .pathParam("x", "berry")
            .pathParam("z", "donut")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedComparisonsFail() {
        given()
            .pathParam("w", "berry")
            .pathParam("x", "apple")
            .pathParam("z", "donut")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "berry")
            .pathParam("z", "donut")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .pathParam("w", "donut")
            .pathParam("x", "cherry")
            .pathParam("z", "apple")
            .pathParam("y", "berry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedZTooShort() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "berry")
            .pathParam("z", "a")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}