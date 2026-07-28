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
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .when()
                .get("/api/ordered4/apple/berry/dates/cherry")
            .then()
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .when()
                .get("/api/ordered4/zebra/yokel/woven/xerox")
            .then()
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValues() {
        given()
            .when()
                .get("/api/ordered4/apple/berry/apple/cherry")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testWTooShort() {
        given()
            .when()
                .get("/api/ordered4/a/berry/dates/cherry")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testWTooLong() {
        given()
            .when()
                .get("/api/ordered4/1234567/berry/dates/cherry")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testXTooShort() {
        given()
            .when()
                .get("/api/ordered4/apple/b/dates/cherry")
            .then()
                .body(equalTo("unordered"));
    }
}