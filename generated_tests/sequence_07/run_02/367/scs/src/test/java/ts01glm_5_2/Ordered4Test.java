package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubjectIncreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
                .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testSubjectDecreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
                .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testSubjectUnorderedWhenLengthsPassButNeitherCondition() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testSubjectUnorderedWhenWLengthTooShort() {
        given()
                .when()
                .get("/api/ordered4/abc/bbbbb/ddddd/ccccc")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testSubjectUnorderedWhenXLengthTooLong() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/abcdefg/ddddd/ccccc")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testSubjectUnorderedWhenYLengthTooShort() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/abc")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}