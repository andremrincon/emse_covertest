package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + java.util.UUID.randomUUID().toString();

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
    public void testAddExcludesConstraintToProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + java.util.UUID.randomUUID().toString();

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
    public void testGetConfigurationActiveFeaturesEvaluatesProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToNonExistentProduct() {
        String productName = "NonExistentProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromNonExistentProduct() {
        String productName = "NonExistentProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToNonExistentProduct() {
        String productName = "NonExistentProduct-" + java.util.UUID.randomUUID().toString();

        given()
            .formParam("sourceFeature", "featureA")
            .formParam("requiredFeature", "featureB")
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToNonExistentProduct() {
        String productName = "NonExistentProduct-" + java.util.UUID.randomUUID().toString();

        given()
            .formParam("sourceFeature", "featureA")
            .formParam("excludedFeature", "featureB")
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesAfterAdding() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByNameReturnsFeatures() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithFeaturesAndConstraints() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}", productName)
            .then().statusCode(204);
    }
}