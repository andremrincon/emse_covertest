package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithNoConstraints() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithSatisfiedRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString();
        String requiredFeature = "feat-req-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithViolatedExcludesConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString();
        String excludedFeature = "feat-exc-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraintAndMissingRequiredFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString();
        String requiredFeature = "feat-req-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithMultipleConstraintsAndDerivedFeatures() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();
        String featureA = "feat-a-" + UUID.randomUUID().toString();
        String featureB = "feat-b-" + UUID.randomUUID().toString();
        String featureC = "feat-c-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("requiredFeature", featureB)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("excludedFeature", featureC)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraintAndMissingSourceFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString();
        String excludedFeature = "feat-exc-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }
}