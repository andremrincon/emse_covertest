package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testTwo() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .when()
            .get("/api/text2txt/two/x/y")
            .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFor() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .when()
            .get("/api/text2txt/for/x/y")
            .then()
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testFour() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .when()
            .get("/api/text2txt/four/x/y")
            .then()
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYou() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .when()
            .get("/api/text2txt/you/x/y")
            .then()
            .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSeeYou() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .when()
            .get("/api/text2txt/see/you/y")
            .then()
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .when()
            .get("/api/text2txt/by/the/way")
            .then()
            .body(equalTo("btw"));
    }
}