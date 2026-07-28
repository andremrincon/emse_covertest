package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAllowOriginOnV1All() {
        String actual = given()
                .when()
                .get("/v1/all")
                .then()
                .extract()
                .header("Access-Control-Allow-Origin");
        org.junit.Assert.assertNull(actual);
    }

    @Test(timeout = 60000)
    public void testAllowMethodsOnV1All() {
        String actual = given()
                .when()
                .get("/v1/all")
                .then()
                .extract()
                .header("Access-Control-Allow-Methods");
        org.junit.Assert.assertNull(actual);
    }

    @Test(timeout = 60000)
    public void testAllowHeadersOnV1All() {
        String actual = given()
                .when()
                .get("/v1/all")
                .then()
                .extract()
                .header("Access-Control-Allow-Headers");
        org.junit.Assert.assertNull(actual);
    }

    @Test(timeout = 60000)
    public void testCacheControlOnV1All() {
        String actual = given()
                .when()
                .get("/v1/all")
                .then()
                .extract()
                .header("Cache-Control");
        org.junit.Assert.assertNull(actual);
    }

    @Test(timeout = 60000)
    public void testAllowOriginOnV1Alpha() {
        String actual = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .extract()
                .header("Access-Control-Allow-Origin");
        org.junit.Assert.assertNull(actual);
    }

    @Test(timeout = 60000)
    public void testAllowMethodsOnV1Alpha() {
        String actual = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .extract()
                .header("Access-Control-Allow-Methods");
        org.junit.Assert.assertNull(actual);
    }

    @Test(timeout = 60000)
    public void testAllowHeadersOnV1Alpha() {
        String actual = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .extract()
                .header("Access-Control-Allow-Headers");
        org.junit.Assert.assertNull(actual);
    }

    @Test(timeout = 60000)
    public void testCacheControlOnV1Alpha() {
        String actual = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .extract()
                .header("Cache-Control");
        org.junit.Assert.assertNull(actual);
    }
}