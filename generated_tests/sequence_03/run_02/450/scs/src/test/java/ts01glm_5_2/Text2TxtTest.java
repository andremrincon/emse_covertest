package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubjectWordTwo() {
        given()
        .when()
            .get("/api/text2txt/two/word2/word3")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectWordFor() {
        given()
        .when()
            .get("/api/text2txt/for/word2/word3")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectWordYou() {
        given()
        .when()
            .get("/api/text2txt/you/word2/word3")
        .then()
            .statusCode(200)
            .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSubjectWordAnd() {
        given()
        .when()
            .get("/api/text2txt/and/word2/word3")
        .then()
            .statusCode(200)
            .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
        .when()
            .get("/api/text2txt/see/you/word3")
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