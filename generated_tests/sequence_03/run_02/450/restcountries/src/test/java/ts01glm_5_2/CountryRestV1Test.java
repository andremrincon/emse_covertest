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
    public void getByAlphaNotFound() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListBadRequestEmptyCodes() {
        given().queryParam("codes", "").when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListNotFound() {
        given().queryParam("codes", "XX;YY;ZZ").when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencyBadRequest() {
        given().when().get("/v1/currency/12").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencyNotFound() {
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameNotFound() {
        given().when().get("/v1/name/NonExistentCountryName12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeNotFound() {
        given().when().get("/v1/callingcode/99999").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalNotFound() {
        given().when().get("/v1/capital/NonExistentCapital12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionNotFound() {
        given().when().get("/v1/region/NonExistentRegion12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregionNotFound() {
        given().when().get("/v1/subregion/NonExistentSubregion12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageNotFound() {
        given().when().get("/v1/lang/NonExistentLang12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void postV1MethodNotAllowed() {
        given().when().post("/v1").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaSuccess() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListSuccess() {
        given().queryParam("codes", "US;CA").when().get("/v1/alpha/").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencySuccess() {
        given().when().get("/v1/currency/USD").then().statusCode(404);
    }
}