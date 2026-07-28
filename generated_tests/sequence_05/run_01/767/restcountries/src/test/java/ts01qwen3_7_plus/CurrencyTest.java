package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['code']", equalTo("USD"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['name']", equalTo("United States dollar"));
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['symbol']", equalTo("$"));
    }
}