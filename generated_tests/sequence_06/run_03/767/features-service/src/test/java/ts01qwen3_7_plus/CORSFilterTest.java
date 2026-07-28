package ts01qwen3_7_plus;

import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        return baseUrl;
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnGetRequest() {
        String baseUrl = getBaseUrl();
        String productName = "TestProduct-" + UUID.randomUUID().toString();

        given()
            .baseUri(baseUrl)
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
            .pathParam("productName", productName)
        .when()
            .get("/products/{productName}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnOptionsRequest() {
        String baseUrl = getBaseUrl();
        String productName = "TestProduct-" + UUID.randomUUID().toString();

        given()
            .baseUri(baseUrl)
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
            .pathParam("productName", productName)
        .when()
            .options("/products/{productName}")
        .then()
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }
}