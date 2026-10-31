package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithNoConstraintsAndNoFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithFeaturesAndNoConstraints() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraintBothActive() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, requiredFeature)
            .then().statusCode(500);

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraintOnlySourceActive() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraintBothActive() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
            .then().statusCode(500);

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraintOneActive() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }
}