package ts01glm_5_2;

import io.restassured.RestAssured;
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
    public void testSubjectTwoReturns2() {
        given()
            .when()
                .get("/api/text2txt/two/any/any")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectForReturns4() {
        given()
            .when()
                .get("/api/text2txt/for/any/any")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectAreReturnsR() {
        given()
            .when()
                .get("/api/text2txt/are/any/any")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYouReturnsCu() {
        given()
            .when()
                .get("/api/text2txt/see/you/any")
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
    public void testSubjectDefaultReturnsEmpty() {
        given()
            .when()
                .get("/api/text2txt/hello/world/test")
            .then()
                .statusCode(200)
                .body(equalTo(""));
    }
}