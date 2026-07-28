package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithRequiresConstraintTriggersDerivedFeatureProcessing() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "EvalProd-" + uuid;
        String sourceFeature = "SourceFeat-" + uuid;
        String requiredFeature = "RequiredFeat-" + uuid;
        String configName = "EvalConfig-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithExcludesConstraintCoversInvalidResultBranch() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "EvalProd-" + uuid;
        String sourceFeature = "SourceFeat-" + uuid;
        String excludedFeature = "ExcludedFeat-" + uuid;
        String configName = "EvalConfig-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithNoConstraintsCoversEmptyConstraintsBranch() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "EvalProd-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String configName = "EvalConfig-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }
}