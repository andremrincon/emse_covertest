package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .when()
            .get("/api/text2txt/two/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtYou() {
        given()
            .when()
            .get("/api/text2txt/you/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given()
            .when()
            .get("/api/text2txt/and/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {
        given()
            .when()
            .get("/api/text2txt/are/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .when()
            .get("/api/text2txt/see/you/y")
            .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheWay() {
        given()
            .when()
            .get("/api/text2txt/by/the/way")
            .then()
            .statusCode(200)
            .body(equalTo("btw"));
    }
}