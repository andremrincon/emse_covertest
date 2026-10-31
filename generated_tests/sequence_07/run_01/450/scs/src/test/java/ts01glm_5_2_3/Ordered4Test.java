package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthsOutOfRange() {
        given()
                .when()
                .get("/api/ordered4/a/b/c/d")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/apple/banana/dragon/cherry")
                .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx")
                .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthsInRangeButNotOrdered() {
        given()
                .when()
                .get("/api/ordered4/apple/apple/apple/apple")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenSomeLengthsOutOfRange() {
        given()
                .when()
                .get("/api/ordered4/zebra/yak/x-ray/wolf")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthExactlySix() {
        given()
                .when()
                .get("/api/ordered4/abcdef/ghijkl/mnopqr/stuvwx")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}