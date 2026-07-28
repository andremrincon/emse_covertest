package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDoFilterWithNonOptionsRequest_1() {
        Response response = given()
                .when()
                .get("/products");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest_1() {
        Response response = given()
                .when()
                .options("/products");

        response.then().statusCode(200);
    }
}