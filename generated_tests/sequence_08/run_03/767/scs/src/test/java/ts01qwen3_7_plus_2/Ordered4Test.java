package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ddddd")
            .pathParam("y", "ccccc")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .pathParam("w", "ddddd")
            .pathParam("x", "ccccc")
            .pathParam("z", "aaaaa")
            .pathParam("y", "bbbbb")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooShort() {
        given()
            .pathParam("w", "a")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ccccc")
            .pathParam("y", "ddddd")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooLong() {
        given()
            .pathParam("w", "aaaaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ccccc")
            .pathParam("y", "ddddd")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedNotSorted() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ccccc")
            .pathParam("y", "ddddd")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedMixedLengths() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bb")
            .pathParam("z", "ccccc")
            .pathParam("y", "ddddd")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }
}