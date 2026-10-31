package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeatures() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().put("/products/" + productName + "/features/" + featureName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProduct() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        String configName = "Conf-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        String configName = "Conf-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        String configName = "Conf-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String feature1 = "Feat1-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        String feature2 = "Feat2-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("requiredFeature", feature2).when().post("/products/" + productName + "/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String feature1 = "Feat1-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        String feature2 = "Feat2-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurations() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String configName = "Conf-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationByName() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String configName = "Conf-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }
}