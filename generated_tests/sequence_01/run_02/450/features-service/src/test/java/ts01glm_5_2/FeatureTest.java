package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.UUID;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void createFeatureExercisesSetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void updateFeatureExercisesSetName() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getFeaturesExercisesGetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void getProductExercisesFeatureGetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void addFeatureToConfigurationExercisesEquals() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void getConfigurationFeaturesExercisesEqualsAndGetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
            .get("/products/{productName}/configurations/{configurationName}/features",
                productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationExercisesEquals() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void addRequiresConstraintExercisesEqualsAndGetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintExercisesEqualsAndGetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureExercisesEquals() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void getConfigurationExercisesFeatureEqualsAndGetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
            .get("/products/{productName}/configurations/{configurationName}",
                productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsExercisesFeatureEquals() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
            .get("/products/{productName}/configurations", productName)
            .then().statusCode(200);
    }
}