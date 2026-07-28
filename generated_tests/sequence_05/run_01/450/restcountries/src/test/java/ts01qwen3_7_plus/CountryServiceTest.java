package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValidMainAcronym() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/EU");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValidOtherAcronym() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/NAFTA");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/INVALIDBLOC123");
        response.then().statusCode(404);
    }
}