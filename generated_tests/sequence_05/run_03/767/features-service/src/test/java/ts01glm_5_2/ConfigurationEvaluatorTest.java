package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
    public void evaluateConfiguration_withNoConstraints_returnsValidConfiguration() {
        String productName = "EvalTestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "BaseFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withRequiresConstraint_activatesDerivedFeature() {
        String productName = "EvalReqProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withExcludesConstraint_blocksConflictingFeature() {
        String productName = "EvalExclProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_getConfigurationFeatures_triggersEvaluation() {
        String productName = "EvalGetProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "GetFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_getConfigurationDetail_triggersEvaluation() {
        String productName = "EvalDetailProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DetailFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_withBothConstraintTypes_evaluatesAllConstraints() {
        String productName = "EvalBothProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatB-" + UUID.randomUUID().toString().substring(0, 8);
        String featureC = "FeatC-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureC).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureC)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA)
            .then()
            .statusCode(201);
    }
}