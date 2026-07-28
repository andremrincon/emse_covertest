package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE = Optional.ofNullable(System.getenv("BASE_URL"))
            .orElse(System.getProperty("api.base", "http://localhost:8080"));

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testGetProductAddsAccessControlAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}", productName);
        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testOptionsRequestSetsAccessControlAllowMethodsHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}/features", productName);
        Assert.assertEquals("POST, PUT, GET, OPTIONS, DELETE", resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        Assert.assertEquals(204, resp.getStatusCode());
    }
}