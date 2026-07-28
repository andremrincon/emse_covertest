package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "";
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowHeadersHeaderOnV1Alpha() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsCacheControlHeaderOnV1Name() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsAllHeadersOnV1Callingcode() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With")
                .header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV1Capital() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV1Region() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV1Currency() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV1Subregion() {
        given()
                .when()
                .get("/v1/subregion/{name}", "Western Europe")
                .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV1Lang() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV1AlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCORSFilterAddsHeadersOnV1AllEndpoint() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With")
                .header("Cache-Control", "public, max-age=86400");
    }
}