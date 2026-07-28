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
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
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
                .get("/v1/alpha/US")
            .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .header("Cache-Control", nullValue());
    }
}