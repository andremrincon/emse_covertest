package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testText2txtTwo() {
        given()
            .when()
                .get("/api/text2txt/two/word2/word3")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2txtFor() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2txtYou() {
        given()
            .when()
                .get("/api/text2txt/you/word2/word3")
            .then()
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2txtAnd() {
        given()
            .when()
                .get("/api/text2txt/and/word2/word3")
            .then()
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2txtAre() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2txtSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/word3")
            .then()
                .body(equalTo("cu"));
    }
}