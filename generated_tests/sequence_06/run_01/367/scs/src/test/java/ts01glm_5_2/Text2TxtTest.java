package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testText2txtTwo() {
        given()
            .when()
                .get("/api/text2txt/two/a/b")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2txtFor() {
        given()
            .when()
                .get("/api/text2txt/for/a/b")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2txtFour() {
        given()
            .when()
                .get("/api/text2txt/four/a/b")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2txtYou() {
        given()
            .when()
                .get("/api/text2txt/you/a/b")
            .then()
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2txtAnd() {
        given()
            .when()
                .get("/api/text2txt/and/a/b")
            .then()
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2txtAre() {
        given()
            .when()
                .get("/api/text2txt/are/a/b")
            .then()
                .body(equalTo("r"));
    }
}