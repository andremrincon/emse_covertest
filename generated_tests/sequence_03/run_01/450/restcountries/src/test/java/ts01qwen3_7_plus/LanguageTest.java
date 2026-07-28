package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String baseUri = System.getenv("BASE_URI");
        if (baseUri == null) {
            baseUri = System.getProperty("baseURI", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testLanguageCoverageViaAlphaCode() {
        given()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLanguageCoverageViaLanguageCode() {
        given()
            .get("/v1/lang/es")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLanguageCoverageViaCountryName() {
        given()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLanguageCoverageViaCurrency() {
        given()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }
}