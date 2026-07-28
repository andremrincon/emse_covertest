package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameFound() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameNotFound() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();
        String featureName = "NonExistentFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
        .when()
            .put("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteNonExistentFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "NonExistentFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName)
            .then().statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConfigurationFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithConstraints() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithNonExistentFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "NonExistentSource")
            .formParam("requiredFeature", "NonExistentRequired")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(500);
    }
}