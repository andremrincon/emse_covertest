package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testFindFeatureByNameFoundViaRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String requiredFeature = "Req-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindFeatureByNameNotFoundViaRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", "NonExistentFeature")
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedTrueViaConfigurationFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        String configurationName = "Cfg-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedFalseViaConfigurationFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        String configurationName = "Cfg-" + UUID.randomUUID();
        String featureName = "NonExistent-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintViaExcludes() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetConfiguration() {
        String productName = "Prod-" + UUID.randomUUID();
        String configurationName = "Cfg-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesList() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActiveFeatures() {
        String productName = "Prod-" + UUID.randomUUID();
        String configurationName = "Cfg-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFindFeatureByNameFoundViaExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindFeatureByNameNotFoundViaExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", "NonExistentFeature")
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductByNameWithFeaturesAndConstraints() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}", productName)
            .then().statusCode(200);
    }
}