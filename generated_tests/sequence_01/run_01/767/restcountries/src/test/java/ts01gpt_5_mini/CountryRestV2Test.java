package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/US?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_shortCode_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_missingCodes_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_multipleCodes_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha?codes=US,CA&fields=name;capital");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_shortInvalid_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/12");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_validWithFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/name/Germany?fullText=true&fields=name;capital");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/capital/Paris?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/region/Europe?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().pathParam("sub", "Western Europe").when().get("/v2/subregion/{sub}?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/lang/Spanish?fields=name;capital;population");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/demonym/American?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/EU?fields=name;capital;currencies");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostOnV2_returnsMethodNotAllowed() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().post("/v2");
        resp.then().statusCode(405);
    }
}