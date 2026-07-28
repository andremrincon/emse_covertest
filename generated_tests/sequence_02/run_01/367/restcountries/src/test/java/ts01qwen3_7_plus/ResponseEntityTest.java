package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetStatusOnNameNotFound() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageOnNameNotFound() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testGetStatusOnCapitalNotFound() {
        given()
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageOnCapitalNotFound() {
        given()
        .when()
            .get("/v1/capital/123")
        .then()
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testGetStatusOnPostNotAllowed() {
        given()
        .when()
            .post("/v1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageOnPostNotAllowed() {
        given()
        .when()
            .post("/v1")
        .then()
            .body(equalTo(""));
    }
}