package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescriptionToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description for feature")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID();

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
    public void testAddExcludesConstraintToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetProduct() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName1 = "Feature1-" + UUID.randomUUID();
        String featureName2 = "Feature2-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaConfigurationFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaConfigurationDetail() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName1 = "Feature1-" + UUID.randomUUID();
        String featureName2 = "Feature2-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName1).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithoutPreExistingFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithoutPreExistingFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(500));
    }
}