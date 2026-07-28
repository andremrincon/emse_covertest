package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testDoFilterWithGetRequest() {

        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");


        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/products");


        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {

        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");


        Response response = given()
                .baseUri(baseUrl)
                .when()
                .options("/products");


        response.then().header("Access-Control-Allow-Origin", "*");
    }
}