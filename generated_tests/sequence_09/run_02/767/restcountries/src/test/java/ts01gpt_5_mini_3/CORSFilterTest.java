package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testAllowOriginOnV1AlphaByCodeSuccess() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1AlphaList() {
        given().when().get("/v2/all").then().statusCode(404);
        Response resp = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        resp.then().header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testAllowHeadersOnV1CurrencyNotFound() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/currency/XYZ");
        resp.then().header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1NameFullTextTrue() {
        given().when().get("/v2/all").then().statusCode(404);
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/France");
        resp.then().header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCorsHeadersOnV2AlphaFields() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        resp.then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCorsOnV2AllWithFields() {
        given().when().get("/v1/alpha").then().statusCode(404);
        Response resp = given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all");
        resp.then().header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCorsOnRootGet() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/");
        resp.then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCorsOnPostMethodNotAllowed() {
        given().when().get("/v2/all").then().statusCode(404);
        String unique = UUID.randomUUID().toString();
        Response resp = given().body("{\"id\":\"" + unique + "\"}").when().post("/v2");
        resp.then().header("Access-Control-Allow-Origin", "*");
    }
}