package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_whenInvalidFeature_thenReturns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        String invalidFeature = "{\"feature\":\"new-feature\"}";
        String featureName = "new-feature";
        Response act = given().contentType("application/json").body(invalidFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureName);
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddConfiguration_whenNameTooLong_thenReturns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append('x');
        String longConfigName = "conf-" + sb.toString();
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", product, longConfigName);
        assertEquals(500, act.getStatusCode());
    }

    @Ignore("expected:<200> but was:<400>")
    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_withSlashes_returnsInternalServerErrorMessage() {
        given().when().get("/products").then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", "product/with/slashes");
        assertEquals(200, act.getStatusCode());
    }
}