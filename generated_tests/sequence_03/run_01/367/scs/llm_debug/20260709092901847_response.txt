package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl",
            System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "banana", "dragon", "cherry")
            .then()
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", "zebra", "yacht", "apple", "mango")
            .then()
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", "mango", "apple", "peach", "zebra")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithShortString() {
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", "ab", "banana", "cherry", "dragon")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithLongString() {
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "banana", "cherry", "verylongstring")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testServerErrorWithEmptyPathSegment() {
        given()
            .urlEncodingEnabled(false)
            .when()
                .get("/api/ordered4/first/second//fourth")
            .then()
                .statusCode(404);
    }
}