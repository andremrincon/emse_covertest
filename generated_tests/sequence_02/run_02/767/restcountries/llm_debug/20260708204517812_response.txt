package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE_URL = System.getProperty("base.url", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given()
        .when()
            .get(BASE_URL + "/v1/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given()
        .when()
            .get(BASE_URL + "/v1/alpha/US")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given()
        .when()
            .get(BASE_URL + "/v1/name/France")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given()
        .when()
            .get(BASE_URL + "/v1/currency/USD")
        .then()
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testFilterChainContinuation() {
        given()
        .when()
            .get(BASE_URL + "/v1/alpha/US")
        .then()
            .body("alpha3Code", equalTo("USA"));
    }
}