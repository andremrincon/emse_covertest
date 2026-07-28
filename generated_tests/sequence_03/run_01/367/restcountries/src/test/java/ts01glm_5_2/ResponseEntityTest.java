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
    public void testGetNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCallingCodeNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/callingcode/abc")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetNameServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/True")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCapitalServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/True")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }
}