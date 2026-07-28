package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateFeatureSetsNameAndProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureSetsName() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "backlit-keyboard";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "RGB backlit keyboard with customizable zones")
        .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesReturnsProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByNameTriggersGetProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        when().get("/products/{productName}", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesTriggersEqualsAndProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "Req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsTriggersGetProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithNameTriggersGetProductAndEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200);
    }
}