package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        given()
        .when()
            .get("/?name=John%20Smith")
        .then()
            .statusCode(200)
            .body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithDefaultName() {
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void testSlowApiWithZeroDelay() {
        given()
            .queryParam("delay", 0)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void testSlowApiWithPositiveDelay() {
        given()
            .queryParam("delay", 1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200)
            .body(equalTo("Result"));
    }

    @Test(timeout = 60000)
    public void testSlowApiWithInvalidStringDelay() {
        given()
            .queryParam("delay", "abc")
        .when()
            .get("/slowApi")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSlowApiWithNegativeDelay() {
        given()
            .queryParam("delay", -1)
        .when()
            .get("/slowApi")
        .then()
            .statusCode(200);
    }
}