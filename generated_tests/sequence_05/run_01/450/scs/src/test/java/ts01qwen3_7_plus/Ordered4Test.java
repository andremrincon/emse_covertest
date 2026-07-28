package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "apple";
        String x = "banana";
        String y = "cherry";
        String z = "delta";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "delta";
        String x = "cherry";
        String y = "banana";
        String z = "apple";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByComparison() {
        String w = "apple";
        String x = "cherry";
        String y = "banana";
        String z = "delta";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByWLengthTooShort() {
        String w = "app";
        String x = "banana";
        String y = "cherry";
        String z = "delta";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByWLengthTooLong() {
        String w = "appleee";
        String x = "banana";
        String y = "cherry";
        String z = "delta";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByXLengthTooShort() {
        String w = "apple";
        String x = "ban";
        String y = "cherry";
        String z = "delta";

        given()
            .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y)
            .then()
                .statusCode(200);
    }
}