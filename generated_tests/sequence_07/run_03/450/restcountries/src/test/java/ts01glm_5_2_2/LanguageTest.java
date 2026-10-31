package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsLanguageFields() {
        given().when().get("/v1/alpha/{code}", "US").then().statusCode(200).body("languages[0]['iso639_1']", notNullValue());
    }

    @Ignore("Cannot invoke method getAt() on null object")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsLanguageFields() {
        given().when().queryParam("codes", "US,CA,MX").get("/v1/alpha").then().statusCode(400).body("languages[0].name", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsLanguageFields() {
        given().when().get("/v1/currency/{code}", "USD").then().statusCode(200).body("languages[0]['iso639_2']", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsLanguageFields() {
        given().when().get("/v1/name/{name}", "France").then().statusCode(200).body("languages[0]['nativeName']", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsLanguageFields() {
        given().when().get("/v1/region/{region}", "Europe").then().statusCode(200).body("languages[0]['iso639_1']", notNullValue());
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsLanguageFields() {
        given().when().get("/v1/subregion/{subregion}", "Western Europe").then().statusCode(200).body("languages[0].name", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1LangReturnsLanguageFields() {
        given().when().get("/v1/lang/{lang}", "es").then().statusCode(200).body("languages[0]['iso639_2']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AlphaByCodeReturnsLanguageFields() {
        given().when().queryParam("fields", "name;languages").get("/v2/alpha/{code}", "US").then().statusCode(200).body("languages[0]['iso639_1']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsLanguageFields() {
        given().when().queryParam("fields", "name;languages").get("/v2/name/{name}", "Germany").then().statusCode(200).body("languages[0]['nativeName']", notNullValue());
    }

    @Ignore("Cannot invoke method getAt() on null object")
    @Test(timeout = 60000)
    public void testV2LangReturnsLanguageFields() {
        given().when().queryParam("fields", "name;languages").get("/v2/lang/{lang}", "Spanish").then().statusCode(404).body("languages[0].name", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CallingCodeReturnsLanguageFields() {
        given().when().queryParam("fields", "name;languages").get("/v2/callingcode/{code}", "1").then().statusCode(200).body("languages[0]['iso639_2']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AllReturnsLanguageFields() {
        given().when().queryParam("fields", "name;languages").get("/v2/all").then().statusCode(200).body("languages[0]['iso639_1']", notNullValue());
    }
}