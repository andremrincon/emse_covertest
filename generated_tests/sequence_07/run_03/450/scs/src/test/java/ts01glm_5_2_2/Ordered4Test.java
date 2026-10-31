package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllLengthFive() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderAllLengthSix() {
        given()
            .when()
                .get("/api/ordered4/zzzzzz/yyyyyy/aaaaaa/bbbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
            .when()
                .get("/api/ordered4/bbbbb/aaaaa/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWLengthBelowBoundary() {
        given()
            .when()
                .get("/api/ordered4/aaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWLengthAboveBoundary() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYLengthBelowBoundary() {
        given()
            .when()
                .get("/api/ordered4/bbbbb/ccccc/eeeee/dddd")
            .then()
                .statusCode(200);
    }
}