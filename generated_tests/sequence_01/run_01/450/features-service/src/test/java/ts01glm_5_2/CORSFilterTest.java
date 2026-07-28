package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnGetRequest() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", equalTo("*"))
            .header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnOptionsPreflightRequest() {
        given()
            .header("Origin", "http://example.com")
            .header("Access-Control-Request-Method", "GET")
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Origin", equalTo("*"))
            .header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"))
            .header("Access-Control-Allow-Headers", equalTo("x-requested-with"))
            .header("Access-Control-Max-Age", equalTo("3600"));
    }

    @Test(timeout = 60000)
    public void testFilterChainProceedsForNonOptionsRequest() {
        String productName = "CORSFilterTest-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }
}