package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanThree() {
        given()
            .pathParam("txt", "hello")
            .pathParam("pat", "ab")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        given()
            .pathParam("txt", "hello world")
            .pathParam("pat", "world")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        given()
            .pathParam("txt", "hello dlrow")
            .pathParam("pat", "world")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseAdjacent() {
        given()
            .pathParam("txt", "worlddlrow")
            .pathParam("pat", "world")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNotAdjacent() {
        given()
            .pathParam("txt", "world hello dlrow")
            .pathParam("pat", "world")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseAndPatAdjacent() {
        given()
            .pathParam("txt", "dlrowworld")
            .pathParam("pat", "world")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseAndPatNotAdjacent() {
        given()
            .pathParam("txt", "dlrow hello world")
            .pathParam("pat", "world")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}