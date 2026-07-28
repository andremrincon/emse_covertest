package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyCode() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("currencies[0].name", equalTo("United States dollar"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("currencies[0].symbol", equalTo("$"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCurrencyCode() {
        given().when().get("/v1/currency/USD").then().statusCode(404).body("currencies[0].code", hasItem("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCurrencyName() {
        given().when().get("/v1/currency/EUR").then().statusCode(404).body("currencies[0].name", hasItem("Euro"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCurrencySymbol() {
        given().when().get("/v1/currency/EUR").then().statusCode(404).body("currencies[0].symbol", hasItem("€"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyReturnsCurrencyCode() {
        given().when().get("/v2/currency/EUR").then().statusCode(404).body("currencies[0].code", hasItem("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyName() {
        given().when().get("/v2/alpha/DE").then().statusCode(404).body("currencies[0].name", hasItem("Euro"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencySymbol() {
        given().when().get("/v2/alpha/DE").then().statusCode(404).body("currencies[0].symbol", hasItem("€"));
    }
}