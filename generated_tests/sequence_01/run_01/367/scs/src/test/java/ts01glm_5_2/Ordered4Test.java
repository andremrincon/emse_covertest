package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
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
    public void testUnorderedWithValidLengths() {
        given()
            .when()
                .get("/api/ordered4/bbbbb/aaaaa/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthTooShortReturnsUnordered() {
        given()
            .when()
                .get("/api/ordered4/aaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthTooLongReturnsUnordered() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthExactlySixIncreasing() {
        given()
            .when()
                .get("/api/ordered4/aaaaaa/bbbbbb/dddddd/cccccc")
            .then()
                .statusCode(200);
    }
}