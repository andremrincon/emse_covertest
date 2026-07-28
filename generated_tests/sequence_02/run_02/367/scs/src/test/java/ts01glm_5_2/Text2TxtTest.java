package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectWordTwo() {
        given()
            .when()
                .get("/api/text2txt/two/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectWordFor() {
        given()
            .when()
                .get("/api/text2txt/for/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectWordYou() {
        given()
            .when()
                .get("/api/text2txt/you/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSubjectWordAre() {
        given()
            .when()
                .get("/api/text2txt/are/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/x")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}