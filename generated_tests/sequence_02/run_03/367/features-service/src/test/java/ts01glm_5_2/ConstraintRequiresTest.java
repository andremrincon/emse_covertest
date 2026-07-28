package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setUp() {
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
    public void testCreateRequiresConstraintCallsSetters() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationSourceActiveRequiredNotActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationBothFeaturesActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(500);

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationSourceNotActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintWithDifferentFeatureNames() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "feat-A-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "feat-B-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationWithActiveFeaturesList() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
                .statusCode(200);
    }
}