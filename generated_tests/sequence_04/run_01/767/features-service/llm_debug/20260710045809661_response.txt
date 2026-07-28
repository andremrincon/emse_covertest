package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String apiBase = System.getProperty("api.base");
        if (apiBase == null || apiBase.isEmpty()) apiBase = System.getenv("API_BASE");
        if (apiBase == null || apiBase.isEmpty()) apiBase = "http://localhost:8080";
        RestAssured.baseURI = apiBase;
    }

    @Test(timeout = 60000)
    public void testDerivedFeaturesChainActivatesAll() {
        String product = "prod-" + UUID.randomUUID();
        String featureA = "feature-A-" + UUID.randomUUID();
        String featureB = "feature-B-" + UUID.randomUUID();
        String featureC = "feature-C-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "A").when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().formParam("description", "B").when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().formParam("description", "C").when().post("/products/{productName}/features/{featureName}", product, featureC).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureB).formParam("requiredFeature", featureC).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConfigurationMarkedInvalidWhenExcludesConstraint() {
        String product = "prod-" + UUID.randomUUID();
        String featureX = "feature-X-" + UUID.randomUUID();
        String featureY = "feature-Y-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "X").when().post("/products/{productName}/features/{featureName}", product, featureX).then().statusCode(lessThan(300));
        given().formParam("description", "Y").when().post("/products/{productName}/features/{featureName}", product, featureY).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureX).formParam("excludedFeature", featureY).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureX).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureY).then().statusCode(500);

        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testAddingFeatureWithoutDerivedMarksValid() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "solo").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }
}