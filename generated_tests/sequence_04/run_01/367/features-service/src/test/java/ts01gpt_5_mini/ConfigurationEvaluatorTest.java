package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersEvaluation_status201() {
        String id = UUID.randomUUID().toString();
        String product = "prod-" + id;
        String configuration = "cfg-" + id;
        String featureA = "feature-A-" + id;
        String featureB = "feature-B-" + id;

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureA).formParam("description", "source feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureB).formParam("description", "required feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", featureA).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDerivedFeatureActivatedAfterAddingSource() {
        String id = UUID.randomUUID().toString();
        String product = "prod-" + id;
        String configuration = "cfg-" + id;
        String featureA = "feature-A-" + id;
        String featureB = "feature-B-" + id;

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureA).formParam("description", "source feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureB).formParam("description", "required feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", featureA).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", configuration).when().get("/products/{productName}/configurations/{configurationName}/features").then().body("$", hasItem(featureB));
    }

    @Test(timeout = 60000)
    public void testRecursiveDerivedFeaturesProcessing_status201() {
        String id = UUID.randomUUID().toString();
        String product = "prod-" + id;
        String configuration = "cfg-" + id;
        String featureA = "feature-A-" + id;
        String featureB = "feature-B-" + id;
        String featureC = "feature-C-" + id;

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureA).formParam("description", "A").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureB).formParam("description", "B").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureC).formParam("description", "C").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", featureB).formParam("requiredFeature", featureC).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", featureA).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRecursiveDerivedFeaturesResultIncludesDeeplyRequiredFeature() {
        String id = UUID.randomUUID().toString();
        String product = "prod-" + id;
        String configuration = "cfg-" + id;
        String featureA = "feature-A-" + id;
        String featureB = "feature-B-" + id;
        String featureC = "feature-C-" + id;

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureA).formParam("description", "A").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureB).formParam("description", "B").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", featureC).formParam("description", "C").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", featureB).formParam("requiredFeature", featureC).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", featureA).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", configuration).when().get("/products/{productName}/configurations/{configurationName}/features").then().body("$", hasItem(featureC));
    }
}