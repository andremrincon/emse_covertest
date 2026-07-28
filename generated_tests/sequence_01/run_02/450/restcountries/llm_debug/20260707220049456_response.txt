package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowOriginHeaderOnGetAll() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowMethodsHeaderOnGetAll() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowHeadersHeaderOnGetAll() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsCacheControlHeaderOnGetAll() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnGetByAlphaCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnGetByName() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnGetByCurrency() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnGetByCallingCode() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnGetByCapital() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnGetByRegion() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV2Alpha() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }
}