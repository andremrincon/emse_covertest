package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithNoConstraintsCoversBasicPath() {
        String productName = "EvalNoConstraints-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithRequiresConstraintDerivesFeatureCoversDerivedBranch() {
        String productName = "EvalReqDerive-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String sourceFeature = "srcF-" + UUID.randomUUID();
        String requiredFeature = "reqF-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithViolatedExcludesConstraintCoversInvalidBranch() {
        String productName = "EvalExcViol-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String sourceFeature = "srcE-" + UUID.randomUUID();
        String excludedFeature = "excE-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithSatisfiedRequiresConstraintCoversValidNoDerivedBranch() {
        String productName = "EvalReqSat-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String sourceFeature = "srcS-" + UUID.randomUUID();
        String requiredFeature = "reqS-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithExcludesConstraintNotViolatedCoversValidNoDerivedBranch() {
        String productName = "EvalExcOk-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String sourceFeature = "srcOk-" + UUID.randomUUID();
        String excludedFeature = "excOk-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithMultipleConstraintsCoversMultipleForLoopIterations() {
        String productName = "EvalMulti-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String featureA = "fA-" + UUID.randomUUID();
        String featureB = "fB-" + UUID.randomUUID();
        String featureC = "fC-" + UUID.randomUUID();
        String featureD = "fD-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureD).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureC)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureB)
            .formParam("excludedFeature", featureD)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }
}