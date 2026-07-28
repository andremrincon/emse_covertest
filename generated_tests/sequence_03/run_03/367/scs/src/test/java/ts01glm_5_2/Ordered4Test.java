package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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
            .body(equalTo("increasing"));
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
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToShortLength() {
        String w = "a";
        String x = "b";
        String z = "d";
        String y = "c";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLongLength() {
        String w = "aaaaaaa";
        String x = "bbbbbbb";
        String z = "ddddddd";
        String y = "ccccccc";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthOkButNeitherIncreasingNorDecreasing() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderWithLengthSix() {
        String w = "aaaaaa";
        String x = "bbbbbb";
        String z = "dddddd";
        String y = "cccccc";

        given()
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }
}