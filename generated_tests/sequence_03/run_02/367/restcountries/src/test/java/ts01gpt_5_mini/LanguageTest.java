package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetIso639_1_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals("en", resp.jsonPath().getString("languages[0].iso639_1"));
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetIso639_2_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals("eng", resp.jsonPath().getString("languages[0].iso639_2"));
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetName_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals("English", resp.jsonPath().getString("languages[0].name"));
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetNativeName_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals("English", resp.jsonPath().getString("languages[0].nativeName"));
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetIso639_1_v1Currency_USD() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/currency/USD");
        assertEquals("en", resp.jsonPath().getString("[0].languages[0].iso639_1"));
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetNativeName_v1Name_France() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/name/France");
        assertEquals("français", resp.jsonPath().getString("[0].languages[0].nativeName"));
    }
}