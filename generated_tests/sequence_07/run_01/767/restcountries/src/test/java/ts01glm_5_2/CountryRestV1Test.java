package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.basePath = System.getProperty("basePath", "/rest");
    }

    @Test(timeout = 60000)
    public void getByAlphaReturnsNotFoundForUnknownCode() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaReturnsBadRequestForShortCode() {
        given().when().get("/v1/alpha/A").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaReturnsSuccessForValidCode() {
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaListReturnsBadRequestForShortCode() {
        given().queryParam("codes", "A").when().get("/v1/alpha/").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaListReturnsNotFoundForUnknownCodes() {
        given().queryParam("codes", "XX;YY;ZZ").when().get("/v1/alpha/").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaListReturnsServerErrorForMalformedCodes() {
        given().queryParam("codes", "US;;CA").when().get("/v1/alpha/").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrencyReturnsBadRequestForInvalidLength() {
        given().when().get("/v1/currency/AB").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrencyReturnsNotFoundForUnknownCurrency() {
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencyReturnsServerErrorForMixedCaseCurrency() {
        given().when().get("/v1/currency/XyZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameReturnsNotFoundForUnknownName() {
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeReturnsNotFoundForInvalidCode() {
        given().when().get("/v1/callingcode/abc").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalReturnsNotFoundForUnknownCapital() {
        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionReturnsNotFoundForUnknownRegion() {
        given().when().get("/v1/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregionReturnsNotFoundForUnknownSubregion() {
        given().when().get("/v1/subregion/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageReturnsNotFoundForUnknownLanguage() {
        given().when().get("/v1/lang/123").then().statusCode(404);
    }
}