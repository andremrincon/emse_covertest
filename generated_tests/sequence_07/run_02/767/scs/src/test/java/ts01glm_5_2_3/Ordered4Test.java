package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class Ordered4Test {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("API_BASE_URL");
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

        RestAssured.given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "ddddd";
        String x = "ccccc";
        String z = "aaaaa";
        String y = "bbbbb";

        RestAssured.given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        String w = "bbbbb";
        String x = "aaaaa";
        String z = "bbbbb";
        String y = "aaaaa";

        RestAssured.given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthFailWTooShort() {
        String w = "aa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        RestAssured.given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthFailWTooLong() {
        String w = "aaaaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        RestAssured.given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthFailXTooShort() {
        String w = "aaaaa";
        String x = "aa";
        String z = "ccccc";
        String y = "ddddd";

        RestAssured.given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }
}