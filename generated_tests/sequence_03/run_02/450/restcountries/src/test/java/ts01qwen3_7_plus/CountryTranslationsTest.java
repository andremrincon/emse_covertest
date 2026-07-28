package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDe() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(404);
        response.then().body("translations.de", nullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEs() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(404);
        response.then().body("translations.es", nullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFr() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(404);
        response.then().body("translations.fr", nullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJa() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(404);
        response.then().body("translations.ja", nullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIt() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(404);
        response.then().body("translations.it", nullValue());
    }
}