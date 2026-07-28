package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithAlphaCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/XYZ");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNotFoundExceptionMapperWithName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/123");
        response.then().statusCode(404);
    }
}