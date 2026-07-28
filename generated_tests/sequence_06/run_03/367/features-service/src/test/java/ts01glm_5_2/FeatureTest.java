package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class FeatureTest {

    @BeforeClass
    public static void setUpClass() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureCoversSetNameAndSetProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test description")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesCoversGetProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/features", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureCoversSetName() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddDifferentFeaturesCoversEqualsDifferentNames() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, "feat-a")
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, "feat-b")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureCoversEqualsSameNameSameProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureCoversGetProductAndEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testSameFeatureNameDifferentProductsCoversEqualsSameNameDiffProduct() {
        String product1 = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String product2 = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Shared-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", product1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}", product2)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", product1, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", product2, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCoversEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesCoversEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationCoversEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testMultipleFeaturesInConfigurationCoversEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "Feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "Feat2-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, feature1)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, feature2)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature2)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDuplicateFeatureInConfigurationCoversEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(500);
    }
}