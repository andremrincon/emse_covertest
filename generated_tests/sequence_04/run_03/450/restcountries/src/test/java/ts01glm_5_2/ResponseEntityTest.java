package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatus_onAlphaCodeNotFound() {
        given()
                .when()
                .get("/v1/alpha/{alphacode}", "XYZ")
                .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatus_onAlphaCodeBadRequest() {
        given()
                .when()
                .get("/v1/alpha/{alphacode}", "123")
                .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatus_onNameNotFound() {
        given()
                .when()
                .get("/v1/name/{name}", "123")
                .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatus_onCapitalNotFound() {
        given()
                .when()
                .get("/v1/capital/{capital}", "123")
                .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatus_onRegionNotFound() {
        given()
                .when()
                .get("/v1/region/{region}", "123")
                .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatus_onCallingCodeNotFound() {
        given()
                .when()
                .get("/v1/callingcode/{callingcode}", "abc")
                .then()
                .statusCode(404)
                .body("message", notNullValue());
    }
}