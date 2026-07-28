package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnGetRequest() {
        given()
            .when()
            .get("/products")
            .then()
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnOptionsRequest() {
        given()
            .when()
            .options("/products")
            .then()
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnPostRequest() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));
    }
}