package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void corsFilterProcessesGetRequestThroughFilterChain() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void corsFilterHandlesOptionsPreflightRequest() {
        given()
            .when()
                .request(Method.OPTIONS, "/products")
            .then()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", containsString("OPTIONS"));
    }

    @Test(timeout = 60000)
    public void corsFilterSetsHeadersOnDeleteRequest() {
        String productName = "CORS-Test-Product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));
        given()
            .when()
                .delete("/products/{productName}", productName)
            .then()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }
}