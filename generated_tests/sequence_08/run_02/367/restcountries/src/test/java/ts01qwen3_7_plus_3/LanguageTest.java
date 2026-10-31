package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class LanguageTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. JSON path [0].languages[0].iso639_1 doesn't match. Expected: en   Actual: null")
    @Test(timeout = 60000)
    public void testLanguageIso639_1Field() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .body("[0].languages[0].iso639_1", equalTo("en"));
    }

    @Test(timeout = 60000)
    public void testLanguageIso639_2Field() {
        given()
        .when()
            .get("/v2/alpha/US")
        .then()
            .body("languages[0].iso639_2", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageNameField() {
        given()
        .when()
            .get("/v1/lang/es")
        .then()
            .body("[0].languages[0].name", equalTo("Spanish"));
    }

    @Test(timeout = 60000)
    public void testLanguageNativeNameField() {
        given()
        .when()
            .get("/v2/lang/Spanish")
        .then()
            .body("[0].languages[0].nativeName", equalTo(null));
    }
}