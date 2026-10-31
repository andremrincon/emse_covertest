package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testIncreasing() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/aaaaa/bbbbb/aaaaa/ccccc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedInvalidLengths() {
        RestAssured.given()
            .when()
            .get("/api/ordered4/a/bbbbb/ccccc/ddddd")
            .then()
            .statusCode(200);
    }
}