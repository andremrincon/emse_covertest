package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost";
        }
        String port = System.getenv("PORT");
        if (port != null && !port.isEmpty()) {
            RestAssured.port = Integer.parseInt(port);
        } else {
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given()
            .when()
                .get("/api/text2txt/two/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given()
            .when()
                .get("/api/text2txt/for/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given()
            .when()
                .get("/api/text2txt/you/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given()
            .when()
                .get("/api/text2txt/are/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given()
            .when()
                .get("/api/text2txt/see/you/y")
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