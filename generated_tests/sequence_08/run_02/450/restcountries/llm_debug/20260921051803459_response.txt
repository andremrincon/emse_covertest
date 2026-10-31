package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CurrencyTest {

    @Before
    public void setUp() {
        String envUrl = System.getenv("BASE_URL");
        String propUrl = System.getProperty("base.url");
        if (envUrl != null && !envUrl.isEmpty()) {
            RestAssured.baseURI = envUrl;
        } else if (propUrl != null && !propUrl.isEmpty()) {
            RestAssured.baseURI = propUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaAlphaCode() {
        Response response = given()
                .when()
                .get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaCurrencyCode() {
        Response response = given()
                .when()
                .get("/v1/currency/USD");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaCountryName() {
        Response response = given()
                .when()
                .get("/v1/name/France");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaRegion() {
        Response response = given()
                .when()
                .get("/v1/region/Europe");
        response.then().statusCode(200);
    }
}