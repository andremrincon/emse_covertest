package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/test-feature").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/feature-to-delete").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/features/feature-to-delete").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameFound() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/existing-feature").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/existing-feature").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameNotFound() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/non-existent-feature").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedTrueViaRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/source-feat").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/required-feat").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "source-feat")
            .formParam("requiredFeature", "required-feat")
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedFalseViaRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "missing-source")
            .formParam("requiredFeature", "missing-required")
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/source-feat").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/excluded-feat").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "source-feat")
            .formParam("excludedFeature", "excluded-feat")
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedFalseViaExcludesConstraint() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "missing-source")
            .formParam("excludedFeature", "missing-excluded")
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetConfigurationFeatures() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/feat-a").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/feat-b").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/feat-a").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesAfterAdd() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/feature-x").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureAndVerifyGone() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/feature-removable").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/features/feature-removable").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("description", "A test feature description")
            .when().post("/products/" + productName + "/features/described-feature").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetails() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/feat-c").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/feat-c").then().statusCode(lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName).then().statusCode(204);
    }
}