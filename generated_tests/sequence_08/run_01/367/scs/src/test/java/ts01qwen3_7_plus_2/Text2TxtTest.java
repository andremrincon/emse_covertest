package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testWord1IsYou() {
        given()
            .when()
            .get("/api/text2txt/you/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWord1IsSeeAndWord2IsYou() {
        given()
            .when()
            .get("/api/text2txt/see/you/y")
            .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testWord1IsByWord2IsTheWord3IsWay() {
        given()
            .when()
            .get("/api/text2txt/by/the/way")
            .then()
            .statusCode(200)
            .body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testWord1IsTwo() {
        given()
            .when()
            .get("/api/text2txt/two/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1IsFor() {
        given()
            .when()
            .get("/api/text2txt/for/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1IsFour() {
        given()
            .when()
            .get("/api/text2txt/four/x/y")
            .then()
            .statusCode(200)
            .body(equalTo("4"));
    }
}