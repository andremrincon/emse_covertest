package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/x")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAre() {
        given()
            .when()
                .get("/api/text2txt/are/x/y")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAnd() {
        given()
            .when()
                .get("/api/text2txt/and/x/y")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYou() {
        given()
            .when()
                .get("/api/text2txt/you/x/y")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFor() {
        given()
            .when()
                .get("/api/text2txt/for/x/y")
            .then()
                .statusCode(200);
    }
}