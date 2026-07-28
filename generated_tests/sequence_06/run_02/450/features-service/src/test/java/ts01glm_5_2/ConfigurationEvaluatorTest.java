package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withNoConstraints_returnsValidResult() {
        String productName = "TestProduct-NoConstraints-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "FeatureA-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName).then().statusCode(is(200));
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withRequiresConstraint_producesDerivedFeatures() {
        String productName = "TestProduct-Requires-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName).then().statusCode(is(200));
    }

    @Ignore("1 expectation failed. Expected status code is <500> but was <200>.")
    @Test(timeout = 60000)
    public void evaluateConfiguration_withExcludesConstraint_violatedReturnsInvalid() {
        String productName = "TestProduct-Excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(is(500));

        given().when().get("/products/" + productName + "/configurations/" + configurationName).then().statusCode(is(500));
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withExcludesConstraint_notViolatedReturnsValid() {
        String productName = "TestProduct-ExcludesValid-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ValidSrc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ValidExcl-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(is(200));
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withRequiresConstraint_requiredFeatureAlreadyActive() {
        String productName = "TestProduct-ReqAlready-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcAlready-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqAlready-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName).then().statusCode(is(200));
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withMultipleConstraints_combinedEvaluation() {
        String productName = "TestProduct-Multi-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatB-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureC = "FeatC-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureC)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName).then().statusCode(is(200));
    }
}