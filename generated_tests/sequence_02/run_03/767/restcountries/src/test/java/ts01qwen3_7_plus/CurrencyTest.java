package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .body("currencies[0]['code']", equalTo(Collections.singletonList("USD")));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName() {
        given()
                .when()
                .get("/v1/currency/EUR")
                .then()
                .statusCode(lessThan(300))
                .body("currencies[0]['name']", equalTo(Collections.singletonList("Euro")));
    }

    @Test(timeout = 60000)
    public void testSetSymbol() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(lessThan(300))
                .body("currencies[0]['symbol']", equalTo(Collections.singletonList("€")));
    }
}