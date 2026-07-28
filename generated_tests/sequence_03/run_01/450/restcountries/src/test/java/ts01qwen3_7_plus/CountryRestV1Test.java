package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE_URL = "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void getByAlpha_invalidLength_returns400() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/alpha/A");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_nullCodes_returns400() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/alpha/");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_invalidCodes_returns400() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/alpha/?codes=US,CA");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_validCodes_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/alpha/?codes=US;CA");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidLength_returns400() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/currency/US");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_validCurrency_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/currency/USD");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByName_validName_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/name/France");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_validCode_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/callingcode/1");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_validCapital_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/capital/London");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_validRegion_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/region/Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubregion_validSubregion_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/subregion/Western%20Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_validLanguage_returns200() {
        given().when().get(BASE_URL + "/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE_URL + "/v1/lang/es");
        response.then().statusCode(200);
    }
}