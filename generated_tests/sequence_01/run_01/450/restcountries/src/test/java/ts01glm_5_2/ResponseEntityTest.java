package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/{name}", "123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetNameServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/{name}", "True")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/{capital}", "123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/region/{region}", "123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetRegionServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/region/{region}", "True")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCurrencyNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v2/currency/{currency}", "XYZ")
                .then()
                .statusCode(404);
    }
}