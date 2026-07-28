package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
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
        String w = "ddddd";
        String x = "ccccc";
        String z = "aaaaa";
        String y = "bbbbb";

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
        String z = "bbbbb";
        String y = "aaaaa";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200)
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithInvalidLengths() {
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
    public void testUnorderedWithTooLongStrings() {
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

    @Test(timeout = 60000)
    public void testIncreasingWithSixCharStrings() {
        String w = "aaaaaa";
        String x = "bbbbbb";
        String z = "dddddd";
        String y = "cccccc";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200)
                .body(containsString("increasing"));
    }
}