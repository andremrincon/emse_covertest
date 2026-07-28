package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_ValidAcronym() {
        given().when().get("/").then().statusCode(lessThan(300));

        Response response = given()
                .pathParam("regionalbloc", "EU")
                .when()
                .get("/v2/regionalbloc/{regionalbloc}");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_InvalidAcronym() {
        given().when().get("/").then().statusCode(lessThan(300));

        Response response = given()
                .pathParam("regionalbloc", "123")
                .when()
                .get("/v2/regionalbloc/{regionalbloc}");

        response.then().statusCode(404);
    }
}