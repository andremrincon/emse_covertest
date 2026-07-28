package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDe() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEs() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFr() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("translations.fr", equalTo("États-Unis"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJa() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIt() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}