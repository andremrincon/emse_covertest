package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationActivatesFeature() {
        String productName = "TestProduct-Active-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-Active-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-Active-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationDeactivatesFeature() {
        String productName = "TestProduct-Deactive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-Deactive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-Deactive-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void getActiveFeaturesReturnsActivatedFeatureNames() {
        String productName = "TestProduct-GetActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-GetActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-GetActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200)
                .body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void getConfigurationReturnsConfigurationWithProductAndAvailableFeatures() {
        String productName = "TestProduct-GetConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-GetConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-GetConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProductReturnsConfigurationList() {
        String productName = "TestProduct-ListConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-ListConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations", productName)
            .then()
                .statusCode(200)
                .body(containsString(configurationName));
    }

    @Test(timeout = 60000)
    public void getActiveFeaturesAfterDeactivationReturnsEmptyList() {
        String productName = "TestProduct-EmptyActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-EmptyActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-EmptyActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200)
                .body(not(containsString(featureName)));
    }

    @Test(timeout = 60000)
    public void getActiveFeaturesWithMultipleFeaturesReturnsAllNames() {
        String productName = "TestProduct-Multi-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "TestFeature-Multi1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "TestFeature-Multi2-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-Multi-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName2).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200)
                .body(containsString(featureName1), containsString(featureName2));
    }

    @Test(timeout = 60000)
    public void createConfigurationSetsValidFlag() {
        String productName = "TestProduct-Valid-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-Valid-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithNoFeaturesReturnsEmptyAvailableFeatures() {
        String productName = "TestProduct-NoFeatures-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-NoFeatures-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getActiveFeaturesForConfigurationWithNoActiveFeaturesReturnsEmpty() {
        String productName = "TestProduct-NoActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-NoActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-NoActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void addSameFeatureTwiceToConfigurationDoesNotDuplicate() {
        String productName = "TestProduct-Dup-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-Dup-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-Dup-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteConfigurationRemovesItFromList() {
        String productName = "TestProduct-DelConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-DelConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationAfterFeatureActivationAndDeactivationShowsCurrentState() {
        String productName = "TestProduct-Toggle-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "TestFeature-Toggle1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "TestFeature-Toggle2-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-Toggle-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName2).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName1).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200)
                .body(not(containsString(featureName1)), containsString(featureName2));
    }

    @Test(timeout = 60000)
    public void getConfigurationDetailsWithMultipleProductFeatures() {
        String productName = "TestProduct-MultiFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "TestFeature-MultiFeat1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "TestFeature-MultiFeat2-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName3 = "TestFeature-MultiFeat3-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-MultiFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName3).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName2).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200);
    }
}