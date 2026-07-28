package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testText2Txt_two() {
        given()
            .when()
                .get("/api/text2txt/two/word2/word3")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_for() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_you() {
        given()
            .when()
                .get("/api/text2txt/you/word2/word3")
            .then()
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_are() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_seeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/word3")
            .then()
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_byTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .body(equalTo("btw"));
    }
}