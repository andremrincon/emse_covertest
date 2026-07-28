package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FeatureTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssured.config()
                .decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"))
                .encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"));
    }

    @Test(timeout = 60000)
    public void testCreateFeatureSetsNameAndProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesReturnsProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testUpdateFeatureSetsName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "UpdateFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .put("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DeleteFeature-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testAddFeatureToConfigurationExercisesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "ConfigFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActiveFeaturesExercisesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "ActiveFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/configurations/{configurationName}/features",
                        productName, configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationExercisesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "RemoveFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateMultipleFeaturesExercisesEqualsInCollection() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, feature1)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, feature2)
                .then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/features", productName)
                .then().statusCode(200)
                .body("size()", greaterThanOrEqualTo(2));
    }

    @Test(timeout = 60000)
    public void testGetProductByNameExercisesFeatureGetProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "ProductFeature-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testUpdateFeatureWithDescriptionExercisesSetName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DescFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().urlEncodingEnabled(true)
                .formParam("description", "Updated description for feature")
                .when()
                .put("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureWithDescriptionExercisesSetProductAndName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DescCreateFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().urlEncodingEnabled(true)
                .formParam("description", "A test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetAllProductsExercisesFeatureEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "AllProductsFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().get("/products")
                .then().statusCode(200);
    }
}