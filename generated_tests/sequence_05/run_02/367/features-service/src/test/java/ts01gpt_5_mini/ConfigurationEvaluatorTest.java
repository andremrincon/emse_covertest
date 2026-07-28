package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddingFeatureToConfigurationActivatesRequiredFeature() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String featureA = "featA-" + uid;
        String featureB = "featB-" + uid;
        String config = "conf-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddingFeatureWithoutConstraintsDoesNotDeriveFeatures() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-no-const-" + uid;
        String feature = "soloFeat-" + uid;
        String config = "conf-no-const-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRecursiveDerivedFeatureActivation() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-rec-" + uid;
        String a = "A-" + uid;
        String b = "B-" + uid;
        String c = "C-" + uid;
        String config = "conf-rec-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, a).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, b).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, c).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", a).formParam("requiredFeature", b).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", b).formParam("requiredFeature", c).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, a).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationReturns204() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-del-" + uid;
        String feature = "delFeat-" + uid;
        String config = "conf-del-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesContainsDerivedFeature() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-get-" + uid;
        String x = "X-" + uid;
        String y = "Y-" + uid;
        String config = "conf-get-" + uid;

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, x).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, y).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", x).formParam("requiredFeature", y).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, x).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasItem(y));
    }
}