package ts01qwen3_7_plus_3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryTranslationsTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testSetTranslationsViaAlphaCode() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/alpha/US");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetTranslationsViaName() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/name/France");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetTranslationsViaCurrency() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/currency/USD");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetTranslationsViaRegion() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/region/Europe");

        response.then().statusCode(200);
    }
}