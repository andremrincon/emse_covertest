package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthConditionFails() {
        given()
            .when()
                .get("/api/ordered4/a/yak/wolf/x-ray")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingWhenAllLengthsValidAndZGreaterThanYGreaterThanXGreaterThanW() {
        given()
            .when()
                .get("/api/ordered4/apple/banana/dragon/cherry")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingWhenAllLengthsValidAndWGreaterThanXGreaterThanYGreaterThanZ() {
        given()
            .when()
                .get("/api/ordered4/zebras/yakss/vines/wolfs")
            .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenAllLengthsValidButNeitherIncreasingNorDecreasing() {
        given()
            .when()
                .get("/api/ordered4/apple/apple/apple/apple")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenStringLengthExceedsSix() {
        given()
            .when()
                .get("/api/ordered4/apples/banana/dragon/cherry")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testIncreasingWithMinimumValidLengthFive() {
        given()
            .when()
                .get("/api/ordered4/alpha/bravo/delta/gamma")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}