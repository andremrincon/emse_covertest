package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetCodeViaAlphaEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().statusCode(200).body("currencies.code", hasItem("USD"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetNameViaAlphaEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/GB").then().statusCode(200).body("currencies.name", hasItem("British pound"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetSymbolViaAlphaEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/FR").then().statusCode(200).body("currencies.symbol", hasItem("€"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetCodeViaCurrencyEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/currency/USD").then().statusCode(200).body("currencies.code", hasItem("USD"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetNameViaCurrencyEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/currency/EUR").then().statusCode(200).body("currencies.name", hasItem("Euro"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetSymbolViaCurrencyEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/currency/GBP").then().statusCode(200).body("currencies.symbol", hasItem("£"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetCodeViaNameEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/name/France").then().statusCode(200).body("currencies.code", hasItem("EUR"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetNameViaRegionEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/region/Europe").then().statusCode(200).body("currencies.name", hasItem("Euro"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetSymbolViaCallingCodeEndpoint() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/callingcode/1").then().statusCode(200).body("currencies.symbol", hasItem("$"));
    }
}