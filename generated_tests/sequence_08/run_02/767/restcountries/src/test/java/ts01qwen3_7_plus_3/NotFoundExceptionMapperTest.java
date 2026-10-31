package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithInvalidAlphaCode() {
        RestAssured.given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = RestAssured.given().when().get("/v1/alpha/XYZ");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithInvalidName() {
        RestAssured.given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response response = RestAssured.given().when().get("/v1/name/123");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithInvalidCapital() {
        RestAssured.given().when().get("/v1/capital/London").then().statusCode(lessThan(300));
        Response response = RestAssured.given().when().get("/v1/capital/123");
        assertEquals(404, response.getStatusCode());
    }
}