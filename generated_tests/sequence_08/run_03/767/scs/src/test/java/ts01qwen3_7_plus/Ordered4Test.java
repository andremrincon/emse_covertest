package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testLengthLessThan5() {
        String w = "a";
        String x = "b";
        String z = "c";
        String y = "d";

        given()
            .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthGreaterThan6() {
        String w = "aaaaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthValidButUnordered() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthValidAndIncreasing() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthValidAndDecreasing() {
        String w = "ddddd";
        String x = "ccccc";
        String z = "aaaaa";
        String y = "bbbbb";

        given()
            .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
            .then()
                .statusCode(200);
    }
}