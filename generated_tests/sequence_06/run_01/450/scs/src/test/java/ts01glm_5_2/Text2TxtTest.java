package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testText2Txt_wordTwo_returns2() {
        given()
            .when()
                .get("/api/text2txt/two/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_wordFor_returns4() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_wordFour_returns4() {
        given()
            .when()
                .get("/api/text2txt/four/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_wordAre_returnsR() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_seeYou_returnsCu() {
        given()
            .when()
                .get("/api/text2txt/see/you/word3")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_byTheWay_returnsBtw() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}