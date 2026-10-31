package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTranslationsViaAlphaCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCurrency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCallingCode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCapital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200);
    }
}