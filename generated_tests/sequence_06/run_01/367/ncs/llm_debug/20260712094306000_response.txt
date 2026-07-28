package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/gammq/5.5/2.3");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserItmaxExceeded() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/gammq/1000.0/1000.0");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/gammq/0.001/1000.0");
        response.then().statusCode(200);
    }
}