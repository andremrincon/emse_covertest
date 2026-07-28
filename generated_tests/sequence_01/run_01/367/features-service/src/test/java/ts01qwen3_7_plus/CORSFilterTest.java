package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testDoFilterWithGetMethod() {


        Response response = given()
                .when()
                .get("/products");


        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsMethod() {


        Response response = given()
                .when()
                .options("/products");


        response.then().statusCode(200);
    }
}