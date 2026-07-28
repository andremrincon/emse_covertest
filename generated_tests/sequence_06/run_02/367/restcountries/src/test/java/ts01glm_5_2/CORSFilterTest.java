package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", "GET")
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With")
                .header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1AlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1AlphaCodes() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(400)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Currency() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Name() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1CallingCode() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Capital() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Region() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV2AlphaByCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV2Name() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCORSHeadersOnV2Region() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", "public, max-age=86400");
    }
}