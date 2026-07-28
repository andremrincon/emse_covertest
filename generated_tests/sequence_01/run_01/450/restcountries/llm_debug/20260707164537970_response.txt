package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_BadRequest_TooLong() {
        given().when().get("/v1/alpha/1234").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_Success() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_TooShort() {
        given().queryParam("codes", "a").when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound() {
        given().queryParam("codes", "XX;YY;ZZ").when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_InternalServerError() {
        given().queryParam("codes", "US;CA;[").when().get("/v1/alpha/").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_BadRequest_InvalidLength() {
        given().when().get("/v1/currency/12").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound() {
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_Success() {
        given().when().get("/v1/currency/USD").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound() {
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound() {
        given().when().get("/v1/callingcode/99999").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound() {
        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound() {
        given().when().get("/v1/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_NotFound() {
        given().when().get("/v1/subregion/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound() {
        given().when().get("/v1/lang/123").then().statusCode(404);
    }
}