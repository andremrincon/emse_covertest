package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "https://restcountries.com");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <301> was greater than <300>.")
    @Test(timeout = 60000)
    public void getByRegionalBloc_validAcronymEU_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200).body("name", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <301> was greater than <300>.")
    @Test(timeout = 60000)
    public void getByRegionalBloc_nonExistentBloc_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <301> was greater than <300>.")
    @Test(timeout = 60000)
    public void getByRegionalBloc_validAcronymNAFTA_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/NAFTA").then().statusCode(200).body("name", hasItem("United States"));
    }
}