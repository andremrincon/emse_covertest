package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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
            .body(containsString("increasing"));
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
            .body(containsString("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ccccc")
            .pathParam("y", "ddddd")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithShortLength() {
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
    public void testUnorderedWithLongLength() {
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
    public void testBoundaryLengthExactlySixIncreasing() {
        given()
            .pathParam("w", "aaaaaa")
            .pathParam("x", "bbbbbb")
            .pathParam("z", "dddddd")
            .pathParam("y", "cccccc")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .body(containsString("increasing"));
    }
}