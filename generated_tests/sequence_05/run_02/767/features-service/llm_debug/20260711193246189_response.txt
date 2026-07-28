package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    private String createProduct() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        return productName;
    }

    private String createFeature(String productName) {
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        return featureName;
    }

    private String createConfiguration(String productName) {
        String configName = "C-" + UUID.randomUUID().toString().substring(0, 8);
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        return configName;
    }

    @Test(timeout = 60000)
    public void testCreateFeatureExercisesWithNameAndSetProduct() {
        String productName = createProduct();
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureExercisesSetName() {
        String productName = createProduct();
        String featureName = createFeature(productName);

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesExercisesGetProduct() {
        String productName = createProduct();
        createFeature(productName);

        given().when()
            .get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductExercisesGetProductOnFeature() {
        String productName = createProduct();
        createFeature(productName);

        given().when()
            .get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExercisesEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureTwiceExercisesEqualsSameNameAndProduct() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddDifferentFeaturesExercisesEqualsDifferentName() {
        String productName = createProduct();
        String featureName1 = createFeature(productName);
        String featureName2 = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName1)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationExercisesEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

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
    public void testGetConfigurationActiveFeaturesExercisesEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features",
                  productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductExercisesEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintExercisesGetProduct() {
        String productName = createProduct();
        String featureName1 = createFeature(productName);
        String featureName2 = createFeature(productName);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureName1)
            .formParam("requiredFeature", featureName2)
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationExercisesGetProductAndEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(200);
    }
}