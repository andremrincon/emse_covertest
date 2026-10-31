package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
            .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
            .get("/api/ordered4/ddddd/ccccc/bbbbb/aaaaa")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByLength() {
        given()
            .when()
            .get("/api/ordered4/a/b/c/d")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByValue() {
        given()
            .when()
            .get("/api/ordered4/aaaaa/bbbbb/ccccc/ccccc")
            .then()
            .statusCode(200);
    }
}