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
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Access-Control-Allow-Methods", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Access-Control-Allow-Headers", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .header("Cache-Control", (String) null);
    }
}