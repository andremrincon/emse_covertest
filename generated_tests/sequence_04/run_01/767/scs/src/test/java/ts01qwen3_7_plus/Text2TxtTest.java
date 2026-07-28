package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .when()
            .get("/api/text2txt/two/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given()
            .when()
            .get("/api/text2txt/for/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtFour() {
        given()
            .when()
            .get("/api/text2txt/four/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtYou() {
        given()
            .when()
            .get("/api/text2txt/you/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given()
            .when()
            .get("/api/text2txt/and/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {
        given()
            .when()
            .get("/api/text2txt/are/x/y")
            .then()
            .statusCode(200);
    }
}