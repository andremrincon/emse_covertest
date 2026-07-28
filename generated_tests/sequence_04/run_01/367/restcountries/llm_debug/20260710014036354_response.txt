package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testResponseEntityNameNotFound() {
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityCapitalNotFound() {
        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityRegionNotFound() {
        given().when().get("/v1/region/123").then().statusCode(404);
    }
}