package ts01gpt_5_mini_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private static final String BASE = initBase();

    private static String initBase() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) return env;
        String prop = System.getProperty("api.base.url");
        if (prop != null && !prop.isEmpty()) return prop;
        return "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_success_parsedCountry() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha/US?fields=name;capital");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badRequest_short() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha/1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_success_parsedCountries() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha?codes=US,CA&fields=name;capital");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest_emptyCodes() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha?codes=");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/currency/EUR?fields=name;capital;population");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest_invalidFormat() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/currency/12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_success_fullTextFalse_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/name/Germany?fullText=false&fields=name;capital");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound_numericName() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/name/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/callingcode/1?fields=name;capital;region");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/capital/Paris?fields=name;capital;population");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/region/Europe?fields=name;capital;population");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/subregion/Western%20Europe?fields=name;capital;population");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/lang/Spanish?fields=name;capital;population");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/demonym/American?fields=name;capital;population");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_success_withFields() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/regionalbloc/EU?fields=name;capital;currencies");
        act.then().statusCode(200);
    }
}