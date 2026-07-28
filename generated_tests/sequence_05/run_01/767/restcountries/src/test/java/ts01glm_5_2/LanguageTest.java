package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testLanguageSetIso639_1ViaV1AlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testLanguageSetIso639_2ViaV1AlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetNameViaV1AlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testLanguageSetNativeNameViaV1AlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['nativeName']", equalTo("English"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetIso639_1ViaV1NameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", equalTo("fr"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetIso639_2ViaV1NameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_2']", equalTo("fra"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetNameViaV1NameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0]['name']", equalTo("French"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetNativeNameViaV1NameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0]['nativeName']", equalTo("français"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetIso639_1ViaV1LangEndpoint() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("languages.iso639_1", hasItem("es"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetIso639_2ViaV1LangEndpoint() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("languages.iso639_2", hasItem("spa"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetNameViaV1LangEndpoint() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("languages.name", hasItem("Spanish"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSetNativeNameViaV1LangEndpoint() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("languages.nativeName", hasItem("Español"));
    }
}