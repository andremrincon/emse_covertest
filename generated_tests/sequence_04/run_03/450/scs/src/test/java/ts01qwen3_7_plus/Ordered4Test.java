package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testOrdered4Increasing() {
        given()
            .when()
            .get("/api/ordered4/apple/banana/cherry/donut")
            .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4Decreasing() {
        given()
            .when()
            .get("/api/ordered4/donut/cherry/banana/apple")
            .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4UnorderedLength() {
        given()
            .when()
            .get("/api/ordered4/app/banana/cherry/donut")
            .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4UnorderedValues() {
        given()
            .when()
            .get("/api/ordered4/apple/apple/cherry/donut")
            .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ServerError() {
        given()
            .when()
            .get("/api/ordered4/first/second//fourth")
            .then()
            .statusCode(404);
    }
}