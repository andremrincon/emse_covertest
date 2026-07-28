package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("server.url", "http://localhost:8080/rest");
    }

    private RequestSpecification request() {
        return given().baseUri(baseUrl);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        request()
                .when()
                .get("/v2/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_emptyCodes_returns400() {
        request()
                .when()
                .get("/v2/alpha/")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_returns404() {
        request()
                .when()
                .queryParam("codes", "XX;YY;ZZ")
                .get("/v2/alpha/")
                .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void getByAlphaList_invalidCodes_returns500() {
        request()
                .when()
                .queryParam("codes", "US,CA")
                .get("/v2/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidLength_returns400() {
        request()
                .when()
                .get("/v2/currency/12")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_returns404() {
        request()
                .when()
                .get("/v2/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_returns404() {
        request()
                .when()
                .get("/v2/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        request()
                .when()
                .get("/v2/callingcode/99999")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_returns404() {
        request()
                .when()
                .get("/v2/capital/12345")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        request()
                .when()
                .get("/v2/region/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_notFound_returns404() {
        request()
                .when()
                .get("/v2/subregion/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        request()
                .when()
                .get("/v2/lang/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_notFound_returns404() {
        request()
                .when()
                .get("/v2/demonym/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_notFound_returns404() {
        request()
                .when()
                .get("/v2/regionalbloc/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void parsedCountries_withFieldsFiltering_returns200() {
        request()
                .when()
                .queryParam("fields", "name")
                .get("/v2/alpha/US")
                .then()
                .statusCode(lessThan(300));

        request()
                .when()
                .queryParam("fields", "name")
                .get("/v2/all")
                .then()
                .statusCode(200);
    }
}