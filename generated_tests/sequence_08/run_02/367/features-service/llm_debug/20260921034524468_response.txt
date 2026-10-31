package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithGetMethod() {
        given().when().get("/products").then().statusCode(lessThan(300));

        Response response = given().when().get("/products");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsMethod() {
        given().when().options("/products").then().statusCode(lessThan(300));

        Response response = given().when().options("/products");

        response.then().statusCode(200);
    }
}