package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void evaluationResultConstructorTriggeredWhenConfigurationIsValidAfterRequiresConstraint() {
        String productName = "EvalTest-Valid-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-valid-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
                .then().statusCode(500);

        given().when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluationResultConstructorTriggeredWhenConfigurationIsInvalidAfterExcludesConstraint() {
        String productName = "EvalTest-Invalid-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-invalid-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
                .then().statusCode(500);

        given().when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluationResultConstructorTriggeredWhenRequiresConstraintNotSatisfied() {
        String productName = "EvalTest-ReqMiss-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-reqmiss-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
                .then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(200);
    }
}