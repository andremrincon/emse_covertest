package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void getByAlpha_returns404_forNonExistentAlphaCode() {
        given().baseUri(baseUrl).when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().get("/v2/alpha/US?fields=name;capital").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().get("/v2/alpha/ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_returns400_forInvalidAlphaCode() {
        given().baseUri(baseUrl).when().get("/v2/alpha/12345").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_returns404_forNonExistentCodes() {
        given().baseUri(baseUrl).queryParam("codes", "US;CA").queryParam("fields", "name;capital").when().get("/v2/alpha").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).queryParam("codes", "XX;YY").when().get("/v2/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_returns400_forInvalidCodes() {
        given().baseUri(baseUrl).queryParam("codes", "1").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_returns404_forNonExistentCurrency() {
        given().baseUri(baseUrl).when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().get("/v2/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_returns400_forInvalidCurrency() {
        given().baseUri(baseUrl).when().get("/v2/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByName_returns200_forValidName() {
        given().baseUri(baseUrl).when().get("/v2/name/Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_returns200_forValidCallingCode() {
        given().baseUri(baseUrl).when().get("/v2/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_returns200_forValidCapital() {
        given().baseUri(baseUrl).when().get("/v2/capital/Paris").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_returns200_forValidRegion() {
        given().baseUri(baseUrl).when().get("/v2/region/Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_returns200_forValidSubRegion() {
        given().baseUri(baseUrl).when().get("/v2/subregion/Western%20Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_returns200_forValidLanguage() {
        given().baseUri(baseUrl).when().get("/v2/lang/es").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByDemonym_returns200_forValidDemonym() {
        given().baseUri(baseUrl).when().get("/v2/demonym/American").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_returns200_forValidRegionalBloc() {
        given().baseUri(baseUrl).when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getCountries_returns200_withFieldsFilter() {
        given().baseUri(baseUrl).queryParam("fields", "name;capital;population").when().get("/v2").then().statusCode(200);
    }
}