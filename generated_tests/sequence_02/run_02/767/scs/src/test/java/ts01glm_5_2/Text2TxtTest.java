package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYouReturnsCu() {
        given()
            .when()
                .get("/api/text2txt/see/you/later")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWayReturnsBtw() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testSubjectDefaultReturnsEmptyString() {
        given()
            .when()
                .get("/api/text2txt/hello/world/test")
            .then()
                .statusCode(200)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testSubjectTwoReturns2() {
        given()
            .when()
                .get("/api/text2txt/two/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectAreReturnsR() {
        given()
            .when()
                .get("/api/text2txt/are/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectAndReturnsN() {
        given()
            .when()
                .get("/api/text2txt/and/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("n"));
    }
}