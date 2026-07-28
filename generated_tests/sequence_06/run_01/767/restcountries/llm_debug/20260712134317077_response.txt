package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV1All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v1/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV1All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v1/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersOnV1All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v1/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlOnV1All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v1/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV2All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v2/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV2All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v2/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersOnV2All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v2/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlOnV2All() {
        String baseUrl = getBaseUrl();
        given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().get(baseUrl + "/v2/all");

        response.then().statusCode(200);
        Assert.assertNull(response.getHeader("Cache-Control"));
    }
}