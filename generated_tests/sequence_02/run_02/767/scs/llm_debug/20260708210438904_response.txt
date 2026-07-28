package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .when()
                .get("/api/text2txt/two/x/y")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given()
            .when()
                .get("/api/text2txt/for/x/y")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFour() {
        given()
            .when()
                .get("/api/text2txt/four/x/y")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtYou() {
        given()
            .when()
                .get("/api/text2txt/you/x/y")
            .then()
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given()
            .when()
                .get("/api/text2txt/and/x/y")
            .then()
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {
        given()
            .when()
                .get("/api/text2txt/are/x/y")
            .then()
                .body(equalTo("r"));
    }
}