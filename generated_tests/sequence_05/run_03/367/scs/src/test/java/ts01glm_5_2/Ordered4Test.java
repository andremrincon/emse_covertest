package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedShortString() {
        given()
            .when()
                .get("/api/ordered4/abcd/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedLongString() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderLength6() {
        given()
            .when()
                .get("/api/ordered4/aaaaaa/bbbbbb/dddddd/cccccc")
            .then()
                .statusCode(200);
    }
}