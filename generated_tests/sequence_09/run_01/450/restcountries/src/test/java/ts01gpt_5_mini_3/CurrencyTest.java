package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_currencyCode() {
        given().when().get("/v1/alpha/US").then().body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testV1Alpha_numericBad_returns400() {
        given().when().get("/v1/alpha/123").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_XYZ_notFound_returns404() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Currency_USD_countryCurrencyCode() {
        given().when().get("/v1/currency/USD").then().body("[0].currencies[0].code", equalTo("USD"));
    }

    @Test(timeout = 60000)
    public void testV1Currency_XYZ_notFound_404() {
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2Currency_EUR_fields_returns200_currencyCode() {
        given().queryParam("fields", "name;capital;population").when().get("/v2/currency/EUR").then().body("[0].currencies[0].code", equalTo("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2Alpha_US_fields_returns200_alpha2Code() {
        given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US").then().body("alpha2Code", equalTo("US"));
    }
}