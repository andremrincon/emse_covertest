package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testResponseEntityOnNameNotFound() {
        given()
            .when()
                .get("/v1/name/NonExistentCountryXYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityOnCurrencyBadRequest() {
        given()
            .when()
                .get("/v2/currency/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityOnNameServerError() {
        given()
            .when()
                .get("/v1/name/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityOnCapitalNotFound() {
        given()
            .when()
                .get("/v2/capital/12345")
            .then()
                .statusCode(404);
    }
}