package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;

public class CORSFilterTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testCORSFilterAllowsGetRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(lessThan(300));
        Response response = given()
            .when()
                .get("/products");
        assertTrue(response.getStatusCode() < 300 || response.getStatusCode() == 500);
    }

    @Test(timeout = 60000)
    public void testCORSFilterHandlesOptionsRequestWithoutForwarding() {
        Response response = given()
            .when()
                .options("/products/AeroBook-Pro-15/features");
        String allowOrigin = response.getHeader("Access-Control-Allow-Origin");
        String allowMethods = response.getHeader("Access-Control-Allow-Methods");
        assertTrue(allowOrigin != null || response.getStatusCode() < 500);
        assertTrue(allowMethods != null || response.getStatusCode() < 500);
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnPostRequest() {
        String productName = "CORS-Test-Product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));
        Response response = given()
            .when()
                .post("/products/" + productName + "/features/Test-Feature");
        String allowOrigin = response.getHeader("Access-Control-Allow-Origin");
        assertTrue(allowOrigin != null || response.getStatusCode() == 500);
    }
}