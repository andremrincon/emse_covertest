package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given()
            .when()
            .get("/api/text2txt/for/quick/brown")
            .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given()
            .when()
            .get("/api/text2txt/and/quick/brown")
            .then()
            .statusCode(200)
            .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .when()
            .get("/api/text2txt/see/you/brown")
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