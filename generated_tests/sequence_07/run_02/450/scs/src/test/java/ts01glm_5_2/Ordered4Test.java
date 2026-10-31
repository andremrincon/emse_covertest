package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testOrdered4IncreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/apple/banana/dragon/cherry")
                .then()
                .statusCode(200)
                .body(containsString("increasing"));
    }

    @Test(timeout = 60000)
    public void testOrdered4DecreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/zebra/vodka/snake/tiger")
                .then()
                .statusCode(200)
                .body(containsString("decreasing"));
    }

    @Test(timeout = 60000)
    public void testOrdered4UnorderedShortStrings() {
        given()
                .when()
                .get("/api/ordered4/a/b/c/d")
                .then()
                .statusCode(200)
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4UnorderedLongStrings() {
        given()
                .when()
                .get("/api/ordered4/aaaaaaa/bbbbbbb/ccccccc/ddddddd")
                .then()
                .statusCode(200)
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4UnorderedEqualStrings() {
        given()
                .when()
                .get("/api/ordered4/apple/apple/apple/apple")
                .then()
                .statusCode(200)
                .body(containsString("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4IncreasingLengthSixBoundary() {
        given()
                .when()
                .get("/api/ordered4/apples/banana/dragon/cherry")
                .then()
                .statusCode(200)
                .body(containsString("increasing"));
    }
}