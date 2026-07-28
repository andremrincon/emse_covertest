package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "A test feature description")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description for testing")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProduct() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();
        String configName = uniqueConfigName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfiguration() {
        String productName = uniqueProductName();
        String feature1 = uniqueFeatureName();
        String feature2 = uniqueFeatureName();
        String configName = uniqueConfigName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, feature1)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, feature2)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, feature1)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, feature2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();
        String configName = uniqueConfigName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();
        String configName = uniqueConfigName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeatures() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct() {
        String productName = uniqueProductName();
        String configName = uniqueConfigName();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        given().when().get("/products")
            .then().statusCode(200);
    }
}