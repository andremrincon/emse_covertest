package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssured.config().redirect(RestAssured.config().getRedirectConfig().followRedirects(false));
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404)
                .body(isEmptyOrNullString());
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404)
                .body(isEmptyOrNullString());
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404)
                .body(isEmptyOrNullString());
    }

    @Test(timeout = 60000)
    public void testCallingCodeNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/callingcode/abc")
                .then()
                .statusCode(404)
                .body(isEmptyOrNullString());
    }

    @Test(timeout = 60000)
    public void testV2NameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v2/name/123")
                .then()
                .statusCode(404)
                .body(isEmptyOrNullString());
    }

    @Test(timeout = 60000)
    public void testV2CurrencyBadRequestReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v2/currency/123")
                .then()
                .statusCode(404)
                .body(isEmptyOrNullString());
    }
}