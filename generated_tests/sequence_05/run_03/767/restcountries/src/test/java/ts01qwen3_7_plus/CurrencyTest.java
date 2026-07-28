package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCode() {
        Response resp = given()
            .when()
                .get("/v1/alpha/US");
        resp.then().statusCode(lessThan(300));
        String code = resp.jsonPath().getString("currencies[0].code");
        assertEquals("USD", code);
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName() {
        Response resp = given()
            .when()
                .get("/v1/alpha/US");
        resp.then().statusCode(lessThan(300));
        String name = resp.jsonPath().getString("currencies[0].name");
        assertEquals("United States dollar", name);
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        Response resp = given()
            .when()
                .get("/v1/alpha/US");
        resp.then().statusCode(lessThan(300));
        String symbol = resp.jsonPath().getString("currencies[0].symbol");
        assertEquals("$", symbol);
    }
}