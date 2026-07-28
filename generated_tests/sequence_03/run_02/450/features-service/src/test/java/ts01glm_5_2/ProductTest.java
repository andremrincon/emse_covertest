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
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().formParam("description", "A test feature description").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaCreateProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetProductWithFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "feature1").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "feature2").then().statusCode(lessThan(300));

        given().when().get("/products/{productName}", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().formParam("description", "Updated description").when().put("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        given().when().get("/products").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
    }
}