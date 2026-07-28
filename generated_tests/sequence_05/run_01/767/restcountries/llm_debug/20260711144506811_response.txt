package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testFilterInitialization() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", nullValue());
    }
}