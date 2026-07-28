package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;

public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationWithRequiresConstraintTriggersEvaluationResult() {
        String productName = "EvalTest-Requires-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "BaseFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "DependentFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Base feature for testing")
                .when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Required feature for testing")
                .when().post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, requiredFeature)
                .then().statusCode(is(500));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithExcludesConstraintTriggersEvaluationResult() {
        String productName = "EvalTest-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Source feature")
                .when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "Excluded feature")
                .when().post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().statusCode(is(200));
    }

    @Test(timeout = 60000)
    public void getConfigurationWithoutConstraintsTriggersEvaluationResult() {
        String productName = "EvalTest-NoConstraints-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "SimpleFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("description", "A simple feature")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(is(200));
    }
}