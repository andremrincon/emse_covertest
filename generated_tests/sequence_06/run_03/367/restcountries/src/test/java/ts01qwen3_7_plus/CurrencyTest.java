package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCodeViaV1Alpha() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].code", equalTo(Arrays.asList("USD")));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetNameViaV1Alpha() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].name", equalTo(Arrays.asList("United States dollar")));
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbolViaV1Alpha() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", equalTo(Arrays.asList("$")));
    }

    @Test(timeout = 60000)
    public void testSetCodeViaV2Currency() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("currencies[0].code", equalTo(Arrays.asList("EUR")));
    }

    @Test(timeout = 60000)
    public void testSetNameViaV2Currency() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("currencies[0].name", equalTo(Arrays.asList("Euro")));
    }

    @Test(timeout = 60000)
    public void testSetSymbolViaV2Currency() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", equalTo(Arrays.asList("€")));
    }
}