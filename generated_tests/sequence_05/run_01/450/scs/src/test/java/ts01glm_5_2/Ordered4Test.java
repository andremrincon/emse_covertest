package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class Ordered4Test {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectIncreasing() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
        .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testSubjectDecreasing() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testSubjectUnorderedValidLengths() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testSubjectLengthTooShortW() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/a/bbbbb/ccccc/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testSubjectLengthTooLongW() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testSubjectLengthTooShortX() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/ordered4/bbbbb/a/ccccc/ddddd")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}