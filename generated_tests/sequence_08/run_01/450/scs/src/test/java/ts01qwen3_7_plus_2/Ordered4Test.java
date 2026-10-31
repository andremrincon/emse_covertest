package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUnorderedShortLength() {
        given()
            .when()
                .get("/api/ordered4/a/b/c/d")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLongLength() {
        given()
            .when()
                .get("/api/ordered4/1234567/1234567/1234567/1234567")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedNotSorted() {
        given()
            .when()
                .get("/api/ordered4/abcde/abcde/abcde/abcde")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .when()
                .get("/api/ordered4/abcde/fghij/klmno/pqrst")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .when()
                .get("/api/ordered4/pqrst/klmno/fghij/abcde")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedMixedLengths() {
        given()
            .when()
                .get("/api/ordered4/abcde/fghijkl/mno/pqrst")
            .then()
                .statusCode(200);
    }
}