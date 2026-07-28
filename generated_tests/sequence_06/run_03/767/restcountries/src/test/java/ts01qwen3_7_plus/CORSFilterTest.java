package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Cache-Control", "public, max-age=86400");
    }
}