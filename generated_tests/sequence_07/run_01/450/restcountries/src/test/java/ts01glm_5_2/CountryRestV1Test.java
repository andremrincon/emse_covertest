package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("base.url", "http://localhost:8080/rest");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByAlphaNotFoundTest() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaBadRequestTest() {
        given().when().get("/v1/alpha/1234").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListBadRequestTest() {
        given().when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListNotFoundTest() {
        given().queryParam("codes", "XX").when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListExceptionTest() {
        given().queryParam("codes", ";;").when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencyBadRequestTest() {
        given().when().get("/v1/currency/12").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencyNotFoundTest() {
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameNotFoundTest() {
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeNotFoundTest() {
        given().when().get("/v1/callingcode/99999").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeExceptionTest() {
        given().when().get("/v1/callingcode/True").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalNotFoundTest() {
        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionNotFoundTest() {
        given().when().get("/v1/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregionNotFoundTest() {
        given().when().get("/v1/subregion/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageNotFoundTest() {
        given().when().get("/v1/lang/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionExceptionTest() {
        given().when().get("/v1/region/True").then().statusCode(404);
    }
}