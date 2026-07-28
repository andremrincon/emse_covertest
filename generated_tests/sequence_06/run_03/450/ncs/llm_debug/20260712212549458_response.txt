package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TriangleClassificationTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBranch_A_Less_Than_Zero() {
        String url = getBaseUrl() + "/api/triangle/0/2/3";
        Response response = given().when().get(url);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBranch_C_Less_Than_Zero() {
        String url = getBaseUrl() + "/api/triangle/2/3/0";
        Response response = given().when().get(url);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBranch_Max_C_Invalid() {
        String url = getBaseUrl() + "/api/triangle/2/2/5";
        Response response = given().when().get(url);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBranch_Equilateral() {
        String url = getBaseUrl() + "/api/triangle/3/3/3";
        Response response = given().when().get(url);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBranch_Isosceles() {
        String url = getBaseUrl() + "/api/triangle/3/3/4";
        Response response = given().when().get(url);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBranch_Scalene() {
        String url = getBaseUrl() + "/api/triangle/2/3/4";
        Response response = given().when().get(url);
        assertEquals(200, response.getStatusCode());
    }
}