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
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void derivedFeatureIsActivatedWhenRequiresConstraintPresent() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().formParam("description", "feature A").when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().formParam("description", "feature B").when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureA).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().assertThat().body("$", hasItem(featureB));
    }

    @Test(timeout = 60000)
    public void addingFeatureToConfigurationWithoutConstraintsResultsInFeaturePresent() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String featureC = "featC-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().formParam("description", "feature C").when().post("/products/{productName}/features/{featureName}", product, featureC).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureC).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().assertThat().body("$", hasItem(featureC));
    }

    @Test(timeout = 60000)
    public void addingFeatureToProductReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));

        given().formParam("description", "new feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String featureD = "featD-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().formParam("description", "feature D").when().post("/products/{productName}/features/{featureName}", product, featureD).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureD).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureD).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "source").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "required").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationByNameReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(200);
    }
}