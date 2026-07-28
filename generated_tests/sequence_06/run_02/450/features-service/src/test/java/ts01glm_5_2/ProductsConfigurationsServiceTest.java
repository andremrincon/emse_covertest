package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturns200WithConfigurationNames() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations")
                .then().statusCode(200).body(containsString(configurationName));
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductWithMultipleConfigurations() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configName1 = "ConfigA-" + UUID.randomUUID();
        String configName2 = "ConfigB-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName2).then().statusCode(lessThan(300));

        String expected = "[\"" + configName1 + "\",\"" + configName2 + "\"]";
        given().when().get("/products/" + productName + "/configurations")
                .then().statusCode(200).body(equalTo(expected));
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductWithNoConfigurationsReturns200Empty() {
        String productName = "TestProduct-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations")
                .then().statusCode(200).body(equalTo("[]"));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturns200WithFeatureNames() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features")
                .then().statusCode(200).body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesWithNoFeaturesReturns200Empty() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features")
                .then().statusCode(200).body(equalTo("[]"));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesAfterRemoveReturnsEmpty() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features")
                .then().statusCode(200).body(equalTo("[]"));
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationReturns204() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationReturns201() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationDuplicateThrowsError() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationViaAddFeatureWithExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
                .param("sourceFeature", featureA)
                .param("excludedFeature", featureB)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationWithRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
                .param("sourceFeature", sourceFeature)
                .param("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationViaRemoveFeatureWithConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
                .param("sourceFeature", featureA)
                .param("excludedFeature", featureB)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
                .then().statusCode(204);
    }
}