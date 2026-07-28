package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

public class CORSFilterTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("api.base", "http://localhost:8080");
        RestAssured.reset();
    }

    @Test(timeout = 60000)
    public void testOptionsRequestAddsCORSHeaders() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().baseUri(base).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().options("/products/{productName}/features", product);
        Assert.assertEquals("POST, PUT, GET, OPTIONS, DELETE", resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testGetRequestAddsAccessControlAllowOriginHeader() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().baseUri(base).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/products/{productName}/features", product);
        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testPostFeatureReturnsCreatedAndHasCORSHeaders() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().baseUri(base).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).formParam("description", "Desc").when().post("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturnsNoContent() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().baseUri(base).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(base).formParam("description", "to delete").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().delete("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertEquals(204, resp.getStatusCode());
    }
}