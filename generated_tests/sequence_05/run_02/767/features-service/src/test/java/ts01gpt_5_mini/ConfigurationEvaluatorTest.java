package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintCausesDerivedFeatureActivation() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-requires-" + uid;
        String featureA = "feature-A-" + uid;
        String featureB = "feature-B-" + uid;
        String config = "config-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturnsCreated() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-feature-" + uid;
        String feature = "feature-new-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));

        Response act = given().formParam("description", "desc " + uid).when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintEndpointReturnsCreated() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-req-endpoint-" + uid;
        String source = "src-" + uid;
        String required = "req-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));

        Response act = given().formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintEndpointReturnsCreated() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-excl-" + uid;
        String source = "src-excl-" + uid;
        String excluded = "excluded-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));

        Response act = given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesIncludesDerivedFeatureAfterAddingSource() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-getcfg-" + uid;
        String featureA = "feature-source-" + uid;
        String featureB = "feature-derived-" + uid;
        String config = "cfg-get-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));

        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        act.then().body("$", hasItem(featureB));
    }
}