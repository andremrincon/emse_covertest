package ts01glm_5_2;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get(BASE_URL + "/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get(BASE_URL + "/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
            .when()
                .get(BASE_URL + "/api/ordered4/bbbbb/aaaaa/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToShortString() {
        given()
            .when()
                .get(BASE_URL + "/api/ordered4/aa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLongString() {
        given()
            .when()
                .get(BASE_URL + "/api/ordered4/aaaaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderWithLengthSixStrings() {
        given()
            .when()
                .get(BASE_URL + "/api/ordered4/aaaaaa/bbbbbb/dddddd/cccccc")
            .then()
                .statusCode(200);
    }
}