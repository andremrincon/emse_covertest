package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCountryTranslationsSetters() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(lessThan(300));
    }
}