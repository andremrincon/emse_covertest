package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testCorsHeadersOnGetRequest() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        Response response = given()
            .baseUri(baseUrl)
        .when()
            .get("/products");

        response.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnOptionsRequest() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        Response response = given()
            .baseUri(baseUrl)
        .when()
            .options("/products");

        response.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }
}