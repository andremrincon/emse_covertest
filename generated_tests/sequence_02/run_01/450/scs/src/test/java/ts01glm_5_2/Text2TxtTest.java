package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .when()
                .get("/api/text2txt/two/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
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
    public void testText2TxtYou() {
        given()
            .when()
                .get("/api/text2txt/you/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {
        given()
            .when()
                .get("/api/text2txt/are/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
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