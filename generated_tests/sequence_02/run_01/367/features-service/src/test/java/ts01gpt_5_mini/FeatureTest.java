package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FeatureTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().formParam("description", "Test feature").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "Feature for list").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "Initial").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().formParam("description", "Updated description").when().put("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "To be deleted").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().formParam("description", "Config Feature").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().formParam("description", "Config Feature Delete").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureOne = "feat-" + UUID.randomUUID().toString();
        String featureTwo = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().formParam("description", "one").when().post("/products/{productName}/features/{featureName}", productName, featureOne).then().statusCode(lessThan(300));
        given().formParam("description", "two").when().post("/products/{productName}/features/{featureName}", productName, featureTwo).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureOne).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureTwo).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "source-" + UUID.randomUUID().toString();
        String requiredFeature = "required-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "sf").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "rf").when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "s").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "e").when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName);
        assertEquals(201, resp.getStatusCode());
    }
}