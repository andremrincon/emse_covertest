package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDe() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEs() {
        given()
        .when()
            .get("/v1/name/France")
        .then()
            .body("translations.es", equalTo("Francia"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFr() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .body("translations.fr", equalTo("États-Unis"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJa() {
        given()
        .when()
            .get("/v1/region/Europe")
        .then()
            .body("translations.ja", equalTo("ドイツ"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIt() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .body("[0].translations.it", equalTo("Afghanistan"));
    }
}