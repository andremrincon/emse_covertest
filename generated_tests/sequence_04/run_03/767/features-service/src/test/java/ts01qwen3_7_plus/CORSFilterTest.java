package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        given().when().get("/products").then().statusCode(lessThan(300));

        Response response = given().when().get("/products");

        response.then().statusCode(200);
    }
}