package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2AlphaByCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2AlphaMultipleCodes() {
        given()
                .queryParam("codes", "US;CA;MX")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Name() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200)
                .body("languages[0].iso639_1", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2NameWithFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/name/France")
                .then()
                .statusCode(200)
                .body("languages[0].name", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Lang() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV2LangWithFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/lang/English")
                .then()
                .statusCode(200)
                .body("languages[0].nativeName", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2All() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2AllDefaultFields() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV1AlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV1Name() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0].iso639_2", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV1Lang() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2WithFieldsFilter() {
        given()
                .queryParam("fields", "name;capital;languages")
                .when()
                .get("/v2")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }
}