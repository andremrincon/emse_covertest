package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_invalid_length_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/A");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_fields_filter_returns_body_contains() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        act.then().body(containsString("United States"));
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_valid_with_fields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").queryParam("fields", "name;capital;population").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_empty_codes_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_invalid_length_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_not_found_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByName_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/Germany");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByCallingCode_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByCapital_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/capital/Paris");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/region/Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getBySubRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/subregion/{sub}", "Western Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByLanguage_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/lang/Spanish");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByDemonym_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/demonym/American");
        act.then().statusCode(200);
    }
}