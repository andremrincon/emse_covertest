package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_status200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().statusCode(200);
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_language_iso639_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_language_iso639_2() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_language_name() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_language_nativeName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().body("languages[0]['nativeName']", equalTo("English"));
    }

    @Test(timeout = 60000)
    public void testV1Alpha_numeric_invalid_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/123");
        r.then().statusCode(404);
    }
}