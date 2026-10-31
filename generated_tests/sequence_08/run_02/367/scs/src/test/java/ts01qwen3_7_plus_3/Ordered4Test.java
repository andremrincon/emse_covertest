package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
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
    public void testIncreasing() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnordered1() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/ccccc/bbbbb/eeeee/ddddd")
            .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnordered2() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/eeeee/ddddd/ccccc/bbbbb")
            .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnordered3() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/ccccc/bbbbb/aaaaa/ddddd")
            .then()
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnordered4() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/aaaaa/ccccc/ddddd/bbbbb")
            .then()
            .body(equalTo("unordered"));
    }
}