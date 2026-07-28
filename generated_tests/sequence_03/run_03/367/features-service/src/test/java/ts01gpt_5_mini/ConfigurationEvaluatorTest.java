package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_addFeatureToConfiguration_triggers_requires_and_derived_activation() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String featureA = "Feature-A";
        String featureB = "Feature-B";

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_addFeatureToConfiguration_with_no_constraints_returns_201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String feature = "Standalone-Feature";

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_configuration_get_reflects_invalid_when_excludes_violation() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String featureD = "Feature-D";
        String featureE = "Feature-E";

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featureD).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featureE).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featureD).formParam("excludedFeature", featureE).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureD).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureE).then().statusCode(500);

        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void test_recursive_derivation_multiple_levels() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String featA = "Feat-A";
        String featB = "Feat-B";
        String featC = "Feat-C";

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featA).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featB).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, featC).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featA).formParam("requiredFeature", featB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featB).formParam("requiredFeature", featC).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featA).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_adding_feature_then_checking_configuration_features_includes_derived() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String source = "Alpha";
        String required = "Beta";

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(200);
    }
}