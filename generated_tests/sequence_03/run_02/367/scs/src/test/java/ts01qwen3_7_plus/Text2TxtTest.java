package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .when()
            .get(BASE_URL + "/api/text2txt/two/quick/brown")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get(BASE_URL + "/api/text2txt/two/quick/brown")
            .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .when()
            .get(BASE_URL + "/api/text2txt/see/you/brown")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get(BASE_URL + "/api/text2txt/see/you/brown")
            .then()
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheWay() {
        given()
            .when()
            .get(BASE_URL + "/api/text2txt/by/the/way")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get(BASE_URL + "/api/text2txt/by/the/way")
            .then()
            .body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {
        given()
            .when()
            .get(BASE_URL + "/api/text2txt/are/quick/brown")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get(BASE_URL + "/api/text2txt/are/quick/brown")
            .then()
            .body(equalTo("r"));
    }
}