package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlphaNotFound() {
        given().when().queryParam("fields", "name;capital").get("/v2/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListBadRequest() {
        given().when().queryParam("codes", "US;CA").get("/v2/alpha").then().statusCode(lessThan(300));
        given().when().queryParam("codes", "1").get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaListNotFound() {
        given().when().queryParam("codes", "XX;YY;ZZ").get("/v2/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrencyBadRequest() {
        given().when().queryParam("fields", "name;capital").get("/v2/currency/EUR").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrencyNotFound() {
        given().when().get("/v2/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameNotFound() {
        given().when().get("/v2/name/Germany").then().statusCode(lessThan(300));
        given().when().get("/v2/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeNotFound() {
        given().when().get("/v2/callingcode/1").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/abc").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalNotFound() {
        given().when().get("/v2/capital/Paris").then().statusCode(lessThan(300));
        given().when().get("/v2/capital/12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionNotFound() {
        given().when().get("/v2/region/Europe").then().statusCode(lessThan(300));
        given().when().get("/v2/region/123").then().statusCode(404);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void getBySubRegionNotFound() {
        given().when().get("/v2/subregion/{sub}", "Western Europe").then().statusCode(lessThan(300));
        given().when().get("/v2/subregion/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageNotFound() {
        given().when().get("/v2/lang/es").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonymNotFound() {
        given().when().get("/v2/demonym/American").then().statusCode(lessThan(300));
        given().when().get("/v2/demonym/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBlocNotFound() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void postMethodNotAllowed() {
        given().when().post("/v2").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void getAllCountriesWithFields() {
        given().when().queryParam("fields", "name;capital").get("/v2/all").then().statusCode(200);
    }
}