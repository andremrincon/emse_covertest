package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testText2Txt_two_returns2() {
        given()
            .when()
                .get("/api/text2txt/two/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2txt_for_returns4() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2txt_you_returnsU() {
        given()
            .when()
                .get("/api/text2txt/you/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2txt_are_returnsR() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2txt_seeYou_returnsCu() {
        given()
            .when()
                .get("/api/text2txt/see/you/word3")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2txt_byTheWay_returnsBtw() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}