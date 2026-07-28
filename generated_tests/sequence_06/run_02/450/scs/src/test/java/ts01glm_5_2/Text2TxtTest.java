package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("base.port", "8080"));
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given()
            .when()
                .get("/api/text2txt/two/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given()
            .when()
                .get("/api/text2txt/for/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testAndReturnsN() {
        given()
            .when()
                .get("/api/text2txt/and/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given()
            .when()
                .get("/api/text2txt/are/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given()
            .when()
                .get("/api/text2txt/see/you/a")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}