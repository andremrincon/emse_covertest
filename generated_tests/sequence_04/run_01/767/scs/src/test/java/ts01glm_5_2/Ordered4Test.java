package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @Before
    public void setUp() {
        String base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200)
                .body(containsString("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "dddddd";
        String x = "cccccc";
        String z = "aaaaaa";
        String y = "bbbbbb";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200)
                .body(containsString("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        String w = "bbbbb";
        String x = "aaaaa";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200)
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithTooShortLength() {
        String w = "a";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200)
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithTooLongLength() {
        String w = "aaaaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200)
                .body(containsString("unordered"));
    }
}