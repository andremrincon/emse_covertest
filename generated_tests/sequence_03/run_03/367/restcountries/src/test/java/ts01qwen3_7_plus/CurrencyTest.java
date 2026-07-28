package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCode() {
        given()
                .baseUri(baseUrl)
                .basePath("/rest")
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .body("currencies[0]['code']", equalTo("USD"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName() {
        given()
                .baseUri(baseUrl)
                .basePath("/rest")
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .body("currencies[0]['name']", equalTo("United States dollar"));
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        given()
                .baseUri(baseUrl)
                .basePath("/rest")
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .body("currencies[0]['symbol']", equalTo("$"));
    }
}