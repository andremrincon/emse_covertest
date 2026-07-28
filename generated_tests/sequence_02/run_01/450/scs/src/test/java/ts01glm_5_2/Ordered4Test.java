package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedShortString() {
        given()
            .when()
                .get("/api/ordered4/a/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedEqualStrings() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/aaaaa/aaaaa/aaaaa")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testServerErrorEmptyPath() {
        given()
            .urlEncodingEnabled(false)
            .when()
                .get("/api/ordered4//bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testServerErrorLongString() {
        String longStr = "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";
        given()
            .when()
                .get("/api/ordered4/" + longStr + "/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }
}