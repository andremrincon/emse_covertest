package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TitleTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleReturnsOne() {
        given()
            .when()
                .get("/api/title/male/mr")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitleReturnsMinusOne() {
        given()
            .when()
                .get("/api/title/male/mrs")
            .then()
                .statusCode(200)
                .body(containsString("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleReturnsZero() {
        given()
            .when()
                .get("/api/title/female/mrs")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitleReturnsMinusOne() {
        given()
            .when()
                .get("/api/title/female/sir")
            .then()
                .statusCode(200)
                .body(containsString("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleReturnsTwo() {
        given()
            .when()
                .get("/api/title/none/dr")
            .then()
                .statusCode(200)
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        given()
            .when()
                .get("/api/title/other/mr")
            .then()
                .statusCode(200)
                .body(containsString("-1"));
    }
}