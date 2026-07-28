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
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";
        given()
            .when()
            .get(baseUrl + "/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "ddddd";
        String x = "ccccc";
        String z = "aaaaa";
        String y = "bbbbb";
        given()
            .when()
            .get(baseUrl + "/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        String w = "bbbbb";
        String x = "bbbbb";
        String z = "bbbbb";
        String y = "bbbbb";
        given()
            .when()
            .get(baseUrl + "/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testStringLengthTooShort() {
        String w = "abc";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";
        given()
            .when()
            .get(baseUrl + "/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testStringLengthTooLong() {
        String w = "aaaaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";
        given()
            .when()
            .get(baseUrl + "/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPartialIncreasingCheckFails() {
        String w = "ccccc";
        String x = "ddddd";
        String z = "eeeee";
        String y = "bbbbb";
        given()
            .when()
            .get(baseUrl + "/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
            .statusCode(200);
    }
}