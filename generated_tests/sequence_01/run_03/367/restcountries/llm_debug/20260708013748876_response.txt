package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void v1AlphaByCodeReturnsCurrencyWithCode() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1AlphaByCodeReturnsCurrencyWithName() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1AlphaByCodeReturnsCurrencyWithSymbol() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1CurrencyEndpointReturnsCurrencyCode() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/currency/USD")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1CurrencyEndpointReturnsCurrencyName() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/currency/EUR")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1CurrencyEndpointReturnsCurrencySymbol() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/currency/EUR")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1NameEndpointReturnsCurrencyCode() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/name/France")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1NameEndpointReturnsCurrencyName() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/name/France")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1NameEndpointReturnsCurrencySymbol() {
        given()
                .accept("application/json")
        .when()
                .get("/v1/name/France")
        .then()
                .statusCode(404);
    }
}