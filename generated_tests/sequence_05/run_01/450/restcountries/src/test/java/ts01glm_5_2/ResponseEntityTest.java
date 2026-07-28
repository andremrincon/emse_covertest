package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetMessageViaNameNotFound() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusViaNameNotFound() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageViaCapitalNotFound() {
        given()
            .when()
                .get("/v1/capital/123")
            .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusViaRegionNotFound() {
        given()
            .when()
                .get("/v1/region/123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageViaV2CurrencyNotFound() {
        given()
            .when()
                .get("/v2/currency/XYZ")
            .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusViaV2CapitalNotFound() {
        given()
            .when()
                .get("/v2/capital/12345")
            .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }
}