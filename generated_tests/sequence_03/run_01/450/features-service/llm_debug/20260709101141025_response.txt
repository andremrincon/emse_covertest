package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String config = "conf-" + uid;
        String feature = "feat-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintActivatesDerivedFeatureChain() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-chain-" + uid;
        String config = "conf-chain-" + uid;
        String featA = "featA-" + uid;
        String featB = "featB-" + uid;
        String featC = "featC-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featC).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featA).formParam("requiredFeature", featB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featB).formParam("requiredFeature", featC).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featA).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasItems(featA, featB, featC));
    }

    @Test(timeout = 60000)
    public void testNoConstraintsOnlySingleFeatureActive() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-single-" + uid;
        String config = "conf-single-" + uid;
        String feature = "onlyFeat-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasSize(1));
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintProducesInvalidConfiguration() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-excl-" + uid;
        String config = "conf-excl-" + uid;
        String featD = "featD-" + uid;
        String featE = "featE-" + uid;
        String featF = "featF-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featD).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featE).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featF).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featD).formParam("requiredFeature", featF).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featD).formParam("excludedFeature", featE).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featD).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featE).then().statusCode(500);
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(false));
    }
}