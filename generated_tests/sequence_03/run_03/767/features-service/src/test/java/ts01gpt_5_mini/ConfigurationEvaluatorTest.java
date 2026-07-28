package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
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
    public void test_add_feature_to_configuration_triggers_required_feature_activation() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String sourceFeature = "src-" + uid;
        String requiredFeature = "req-" + uid;
        String config = "conf-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_add_feature_to_configuration_without_constraints_returns_201() {
        String uid = UUID.randomUUID().toString();
        String product = "prod2-" + uid;
        String feature = "feat-" + uid;
        String config = "conf2-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_get_configuration_contains_derived_feature_after_require_constraint() {
        String uid = UUID.randomUUID().toString();
        String product = "prod3-" + uid;
        String sourceFeature = "src3-" + uid;
        String requiredFeature = "req3-" + uid;
        String config = "conf3-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200).body("activedFeatures.name", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void test_delete_feature_from_configuration_returns_204() {
        String uid = UUID.randomUUID().toString();
        String product = "prod4-" + uid;
        String feature = "feat4-" + uid;
        String config = "conf4-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(204);
    }
}