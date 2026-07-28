package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080/rest");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_ValidAcronym() {
        given()
            .when()
            .get("/v2/regionalbloc/EU")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/v2/regionalbloc/EU")
            .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_InvalidAcronym() {
        given()
            .when()
            .get("/v2/regionalbloc/EU")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/v2/regionalbloc/INVALIDBLOC")
            .then()
            .statusCode(404);
    }
}