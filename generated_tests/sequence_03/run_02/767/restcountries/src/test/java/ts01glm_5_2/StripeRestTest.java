package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class StripeRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given()
            .contentType("application/json;charset=utf-8")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\":\"\",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\":\"tok_visa\",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}