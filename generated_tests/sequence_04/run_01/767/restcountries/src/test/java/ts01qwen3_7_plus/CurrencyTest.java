package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    private static final String BASE_URL = System.getProperty("base.url", "http://localhost:8080/rest");

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCode() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("currencies[0].code", equalTo("${code}"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("currencies[0].name", equalTo("${name}"));
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("currencies[0].symbol", equalTo("${symbol}"));
    }
}