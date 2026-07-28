package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsCurrencyCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/alpha/US");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsCurrencyName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/alpha/GB");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsCurrencySymbol() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/alpha/FR");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyCodeReturnsCountriesWithCurrencyFields() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/currency/USD");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyEURReturnsCountriesWithCurrencyName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/currency/EUR");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyXOFReturnsCountriesWithCurrencySymbol() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/currency/XOF");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2GetByAlphaCodeReturnsCurrencyCode() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v2/alpha/US");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCountriesWithCurrencyNameAndSymbol() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().queryParam("fields", "name;capital;currencies").get("/v2/regionalbloc/EU");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2GetByCurrencyReturnsCountriesWithCurrencySymbol() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v2/currency/EUR");

        response.then().statusCode(200);
        response.then().body("currencies", notNullValue());
    }
}