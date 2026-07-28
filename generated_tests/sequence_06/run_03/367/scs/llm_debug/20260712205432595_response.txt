package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ddddd")
            .pathParam("y", "ccccc")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .pathParam("w", "ddddd")
            .pathParam("x", "ccccc")
            .pathParam("z", "aaaaa")
            .pathParam("y", "bbbbb")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ccccc")
            .pathParam("y", "bbbbb")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithInvalidLengths() {
        given()
            .pathParam("w", "ab")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ccccc")
            .pathParam("y", "ddddd")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testServerErrorWithExcessiveLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) {
            sb.append('y');
        }
        String longString = sb.toString();
        given()
            .pathParam("w", "aaaaa")
            .pathParam("x", "bbbbb")
            .pathParam("z", "ccccc")
            .pathParam("y", longString)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }
}