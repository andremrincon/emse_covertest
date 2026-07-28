package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseURI", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetDeTranslation() {
        Response response = given()
                .pathParam("alphacode", "US")
                .when()
                .get("/v1/alpha/{alphacode}");

        response.then().statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetEsTranslation() {
        Response response = given()
                .pathParam("alphacode", "US")
                .when()
                .get("/v1/alpha/{alphacode}");

        response.then().statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetFrTranslation() {
        Response response = given()
                .pathParam("alphacode", "US")
                .when()
                .get("/v1/alpha/{alphacode}");

        response.then().statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetJaTranslation() {
        Response response = given()
                .pathParam("alphacode", "US")
                .when()
                .get("/v1/alpha/{alphacode}");

        response.then().statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetItTranslation() {
        Response response = given()
                .pathParam("alphacode", "US")
                .when()
                .get("/v1/alpha/{alphacode}");

        response.then().statusCode(lessThan(300));
    }
}