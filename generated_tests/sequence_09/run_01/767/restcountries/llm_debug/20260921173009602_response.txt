package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("api.base"))
                .orElse(Optional.ofNullable(System.getenv("API_BASE")).orElse("http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_triggersLanguageSetters_viaAlphaEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{alphacode}", "US").andReturn();
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_triggersLanguageSetters_viaCurrencyEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "USD").andReturn();
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Name_France_triggersLanguageSetters_viaNameEndpoint_withFullTextFalse() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/{name}", "France").andReturn();
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Lang_es_triggersLanguageSetters_viaLangEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/{lang}", "es").andReturn();
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Alpha_US_withFields_triggersLanguageSetters_viaV2AlphaEndpoint() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{alphacode}", "US").andReturn();
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Name_Germany_fullTextTrue_bodyContainsName_assertByBody() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v2/name/{name}", "Germany").andReturn();
        resp.then().body("[0].name", equalTo("Germany"));
    }
}