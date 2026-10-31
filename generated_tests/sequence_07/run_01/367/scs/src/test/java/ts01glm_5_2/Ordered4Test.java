package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllLength5() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .body(containsString("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderAllLength5() {
        String w = "ddddd";
        String x = "ccccc";
        String z = "aaaaa";
        String y = "bbbbb";
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .body(containsString("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooShort() {
        String w = "abcd";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedAllEqualStrings() {
        String w = "bbbbb";
        String x = "bbbbb";
        String z = "bbbbb";
        String y = "bbbbb";
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllLength6() {
        String w = "aaaaaa";
        String x = "bbbbbb";
        String z = "dddddd";
        String y = "cccccc";
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .body(containsString("increasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthTooLong() {
        String w = "aaaaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";
        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .body(containsString("unordered"));
    }
}