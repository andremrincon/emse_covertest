package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("API_BASE");
            if (e2 == null || e2.isEmpty()) {
                RestAssured.baseURI = "http://localhost:8080/rest";
            } else {
                RestAssured.baseURI = e2;
            }
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void getByAlpha_valid_US_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{code}", "US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalid_short_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{code}", "A");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{code}", "ZZZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{code}", "US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_semicolon_codes_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US;CA").when().get("/v2/alpha/");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_comma_codes_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v2/alpha/");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_valid_EUR_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/{currency}", "EUR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalid_length_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/{currency}", "EU");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByName_fullText_and_fields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", true).queryParam("fields", "name;capital;population").when().get("/v2/name/{name}", "Germany");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/callingcode/{code}", "1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/callingcode/{code}", "99999");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/region/{region}", "Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubregion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/subregion/{subregion}", "Western Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void postOnV2_returns405() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().post("/v2");
        resp.then().statusCode(405);
    }
}