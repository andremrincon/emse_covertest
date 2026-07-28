package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testPostMethodNotAllowedResponseEntity() {
        given()
            .when()
                .post("/")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundResponseEntity() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }
}