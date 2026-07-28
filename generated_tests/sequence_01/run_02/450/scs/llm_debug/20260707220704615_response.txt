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
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testLengthsOutOfBounds() {
        given()
            .pathParam("w", "a")
            .pathParam("x", "b")
            .pathParam("z", "c")
            .pathParam("y", "d")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "dates")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .pathParam("w", "zebra")
            .pathParam("x", "yaks_")
            .pathParam("z", "viprs")
            .pathParam("y", "wolfs")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedInBounds() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "apple")
            .pathParam("z", "apple")
            .pathParam("y", "apple")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .body(equalTo("unordered"));
    }
}