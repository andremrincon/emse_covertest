package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectTwo() {
        given()
            .when()
                .get("/api/text2txt/two/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectFor() {
        given()
            .when()
                .get("/api/text2txt/for/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectYou() {
        given()
            .when()
                .get("/api/text2txt/you/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSubjectAnd() {
        given()
            .when()
                .get("/api/text2txt/and/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testSubjectAre() {
        given()
            .when()
                .get("/api/text2txt/are/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/brown")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }
}