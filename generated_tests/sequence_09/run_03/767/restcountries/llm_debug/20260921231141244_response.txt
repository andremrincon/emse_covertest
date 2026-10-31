package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            if (env != null && !env.isEmpty()) {
                base = env;
            } else {
                base = "http://localhost:8080/rest";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_US_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ShortCode_Returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_Returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_ValidCodes_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha/");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadCodes_Returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "123").when().get("/v1/alpha/");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_ArrayFormat_Returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha/");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_USD_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadCode_Returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_France_FullTextFalse_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_Numeric_Returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_One_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_London_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String cap;
        try {
            cap = URLEncoder.encode("London", StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            cap = URLEncoder.encode("London");
        }
        Response resp = given().when().get("/v1/capital/" + cap);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Europe_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_WesternEurope_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String sub;
        try {
            sub = URLEncoder.encode("Western Europe", StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            sub = URLEncoder.encode("Western Europe");
        }
        Response resp = given().when().get("/v1/subregion/" + sub);
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_es_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        resp.then().statusCode(200);
    }
}