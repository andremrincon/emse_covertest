package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
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
    public void testUnorderedWithValidLengths() {
        given()
            .when()
                .get("/api/ordered4/bbbbb/aaaaa/ccccc/ddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithShortString() {
        given()
            .when()
                .get("/api/ordered4/aa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithLongString() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testServerErrorWithEmptyString() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ ")
            .then()
                .statusCode(404);
    }
}