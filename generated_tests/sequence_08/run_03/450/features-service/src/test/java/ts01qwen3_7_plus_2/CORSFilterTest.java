package ts01qwen3_7_plus_2;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnGETRequest() {
        Response response = given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/products");

        response.then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testCORSFilterHandlesOPTIONSRequest() {
        Response response = given()
            .baseUri("http://localhost:8080")
        .when()
            .options("/products");

        response.then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void testCORSFilterWithPOSTRequest() {
        String productName = "TestProduct-" + System.currentTimeMillis();

        Response response = given()
            .baseUri("http://localhost:8080")
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}");

        response.then()
            .statusCode(201)
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }
}