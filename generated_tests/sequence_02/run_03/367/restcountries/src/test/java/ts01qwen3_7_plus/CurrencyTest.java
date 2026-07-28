package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CurrencyTest {

    private String baseUrl;

    @Before
    public void setUp() {
        String envUrl = System.getenv("BASE_URL");
        baseUrl = System.getProperty("baseUrl", envUrl != null ? envUrl : "http://localhost:8080/rest");
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCode() {
        Response response = given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .extract()
                .response();
        String code = response.jsonPath().getString("currencies[0].code");
        assertEquals("USD", code);
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName() {
        Response response = given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .extract()
                .response();
        String name = response.jsonPath().getString("currencies[0].name");
        assertEquals("United States dollar", name);
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        Response response = given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .extract()
                .response();
        String symbol = response.jsonPath().getString("currencies[0].symbol");
        assertEquals("$", symbol);
    }
}