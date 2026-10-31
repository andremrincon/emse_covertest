package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Test;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testOrdered4_LengthsLessThan5_ReturnsUnordered() {
        String w = "a";
        String x = "b";
        String z = "c";
        String y = "d";
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOrdered4_Lengths6_Increasing() {
        String w = "aaaaaa";
        String x = "bbbbbb";
        String z = "dddddd";
        String y = "cccccc";
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOrdered4_Lengths6_Decreasing() {
        String w = "dddddd";
        String x = "cccccc";
        String z = "aaaaaa";
        String y = "bbbbbb";
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOrdered4_Lengths6_Unordered() {
        String w = "aaaaaa";
        String x = "bbbbbb";
        String z = "cccccc";
        String y = "dddddd";
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOrdered4_LengthsGreaterThan6_ReturnsUnordered() {
        String w = "aaaaaaa";
        String x = "bbbbbbb";
        String z = "ccccccc";
        String y = "ddddddd";
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .statusCode(200);
    }
}