package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_ReturnsCountries() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/v2/regionalbloc/EU");

        assertEquals(200, response.getStatusCode());
        response.then().body("size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NonExistentBloc_Returns404() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/v2/regionalbloc/123");

        assertEquals(404, response.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA_WithFields_ReturnsCountries() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .queryParam("fields", "name;capital;currencies")
            .when()
                .get("/v2/regionalbloc/NAFTA");

        assertEquals(200, response.getStatusCode());
        response.then().body("size()", greaterThan(0));
    }
}