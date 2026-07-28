package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testText2TxtWordTwo() {
        given()
            .when()
                .get("/api/text2txt/two/quick/brown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtWordFor() {
        given()
            .when()
                .get("/api/text2txt/for/quick/brown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtWordYou() {
        given()
            .when()
                .get("/api/text2txt/you/quick/brown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtWordAnd() {
        given()
            .when()
                .get("/api/text2txt/and/quick/brown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtWordAre() {
        given()
            .when()
                .get("/api/text2txt/are/quick/brown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/soon")
            .then()
                .statusCode(200);
    }
}