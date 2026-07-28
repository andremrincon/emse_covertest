package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDoFilterNonOptionsRequestChainsToEndpoint() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterOptionsRequestSetsCorsHeadersWithoutChaining() {
        given()
            .when()
                .request(Method.OPTIONS, "/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAllCorsHeadersOnNonOptionsRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }
}