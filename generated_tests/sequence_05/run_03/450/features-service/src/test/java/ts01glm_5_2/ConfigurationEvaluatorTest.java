package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithNoConstraints() {
        String productName = "EvalNoConstraints-" + java.util.UUID.randomUUID();
        String featureName = "BaseFeature-" + java.util.UUID.randomUUID();
        String configurationName = "Config-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithSatisfiedRequiresConstraint() {
        String productName = "EvalReqSat-" + java.util.UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + java.util.UUID.randomUUID();
        String configurationName = "Config-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithViolatedRequiresConstraint() {
        String productName = "EvalReqViol-" + java.util.UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + java.util.UUID.randomUUID();
        String configurationName = "Config-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithExcludesConstraintBothActive() {
        String productName = "EvalExclBoth-" + java.util.UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID();
        String excludedFeature = "ExcludedFeature-" + java.util.UUID.randomUUID();
        String configurationName = "Config-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationTriggersDerivedFeatures() {
        String productName = "EvalDerived-" + java.util.UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + java.util.UUID.randomUUID();
        String configurationName = "Config-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithMultipleConstraintsAndDerivedFeatures() {
        String productName = "EvalMulti-" + java.util.UUID.randomUUID();
        String featureA = "FeatureA-" + java.util.UUID.randomUUID();
        String featureB = "FeatureB-" + java.util.UUID.randomUUID();
        String featureC = "FeatureC-" + java.util.UUID.randomUUID();
        String configurationName = "Config-" + java.util.UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureC)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(lessThan(300));
    }
}