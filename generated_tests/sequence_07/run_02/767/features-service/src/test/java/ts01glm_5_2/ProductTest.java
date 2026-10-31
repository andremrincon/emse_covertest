package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    private String uniqueProductName() {
        return "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueFeatureName() {
        return "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueConfigName() {
        return "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameNotFound() {
        String productName = uniqueProductName();
        String existingFeature = uniqueFeatureName();
        String nonExistentFeature = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + existingFeature).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + nonExistentFeature)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = uniqueProductName();
        String sourceFeature = uniqueFeatureName();
        String requiredFeature = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintNonExistentSource() {
        String productName = uniqueProductName();
        String existingFeature = uniqueFeatureName();
        String requiredFeature = uniqueFeatureName();
        String nonExistentSource = "NonExistent-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + existingFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", nonExistentSource)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = uniqueProductName();
        String sourceFeature = uniqueFeatureName();
        String excludedFeature = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = uniqueProductName();
        String configurationName = uniqueConfigName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddNonExistentFeatureToConfiguration() {
        String productName = uniqueProductName();
        String configurationName = uniqueConfigName();
        String existingFeature = uniqueFeatureName();
        String nonExistentFeature = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + existingFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + nonExistentFeature)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = uniqueProductName();
        String configurationName = uniqueConfigName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfiguration() {
        String productName = uniqueProductName();
        String configurationName = uniqueConfigName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description for testing")
        .when()
            .put("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProduct() {
        String productName = uniqueProductName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct() {
        String productName = uniqueProductName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        given().when().get("/products")
            .then().statusCode(200);
    }
}