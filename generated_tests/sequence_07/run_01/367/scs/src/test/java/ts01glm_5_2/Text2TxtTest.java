package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testSubjectTwoAbbreviation() {
        given()
            .when()
                .get("/api/text2txt/two/hello/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectForAbbreviation() {
        given()
            .when()
                .get("/api/text2txt/for/hello/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectAndAbbreviation() {
        given()
            .when()
                .get("/api/text2txt/and/hello/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectAreAbbreviation() {
        given()
            .when()
                .get("/api/text2txt/are/hello/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYouAbbreviation() {
        given()
            .when()
                .get("/api/text2txt/see/you/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWayAbbreviation() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200);
    }
}