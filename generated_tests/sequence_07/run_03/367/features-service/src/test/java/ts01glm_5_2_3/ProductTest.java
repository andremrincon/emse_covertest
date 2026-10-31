package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductCoversAddFeatureMethod() {
        String productName = "TestProduct-AddFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, "TestFeature-" + UUID.randomUUID().toString().substring(0, 8))
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromProductCoversRemoveFeatureMethod() {
        String productName = "TestProduct-RemoveFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "FeatureToRemove-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature to be removed")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintCoversAddFeatureConstraintMethod() {
        String productName = "TestProduct-Requires-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Source feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Required feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintCoversAddFeatureConstraintMethod() {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Source feature for excludes")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Excluded feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesCoversBuildWithFeaturesMethod() {
        String productName = "TestProduct-BuildFeatures-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "ConfigFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature for configuration")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getProductFeaturesAfterAddingMultipleFeaturesCoversAddFeatureLoop() {
        String productName = "TestProduct-MultiFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "MultiFeature1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "MultiFeature2-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "First feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feature1)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Second feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feature2)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/features", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationCoversRemoveFeatureViaConfiguration() {
        String productName = "TestProduct-ConfigRemove-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "ConfigRemove-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "ConfigFeatRemove-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature for config removal test")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void getProductByNameCoversBuildWithFeaturesRetrieval() {
        String productName = "TestProduct-GetByName-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "GetByNameFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature for product retrieval test")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeatureDescriptionCoversFeatureUpdatePath() {
        String productName = "TestProduct-UpdateFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "UpdateFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Original description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Updated description for feature")
                .when()
                .put("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationCoversBuildWithFeaturesInConfigContext() {
        String productName = "TestProduct-ConfigAdd-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "ConfigAdd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "ConfigAddFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature for config add test")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProductCoversProductWithFeaturesRetrieval() {
        String productName = "TestProduct-GetConfigs-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "GetConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "GetConfigsFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature for configs retrieval test")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteProductCoversProductWithFeaturesDeletion() {
        String productName = "TestProduct-Delete-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DeleteFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature for product deletion test")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}", productName)
                .then()
                .statusCode(204);
    }
}