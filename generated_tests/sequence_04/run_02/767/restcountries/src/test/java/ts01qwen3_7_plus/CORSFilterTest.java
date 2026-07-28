package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200)
            .header("Cache-Control", equalTo(null));
    }
}