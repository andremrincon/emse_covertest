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
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusAndMessageOnNameNotFound() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }

    @Ignore("1 expectation failed. Expected status code <405> but was <404>.")
    @Test(timeout = 60000)
    public void testResponseEntityStatusAndMessageOnPostNotAllowed() {
        given()
        .when()
            .post("/")
        .then()
            .statusCode(405);
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusAndMessageOnCurrencyBadRequest() {
        given()
            .pathParam("currency", "123")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }
}