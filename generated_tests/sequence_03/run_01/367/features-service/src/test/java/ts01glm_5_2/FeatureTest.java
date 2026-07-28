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
        RestAssured.baseURI = System.getProperty("app.url", "http://localhost:8080");
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
    public void testCreateFeature() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test description")
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Original description")
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
                .put("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeatures() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();
        String configName = uniqueConfigName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSecondFeatureToConfiguration() {
        String productName = uniqueProductName();
        String featureName1 = uniqueFeatureName();
        String featureName2 = uniqueFeatureName();
        String configName = uniqueConfigName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName1)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName2)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();
        String configName = uniqueConfigName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();
        String configName = uniqueConfigName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = uniqueProductName();
        String sourceFeature = uniqueFeatureName();
        String requiredFeature = uniqueFeatureName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
                .statusCode(lessThan(300));

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
        String productName = uniqueProductName();
        String sourceFeature = uniqueFeatureName();
        String excludedFeature = uniqueFeatureName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

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
    public void testDeleteFeatureFromProduct() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct() {
        String productName = uniqueProductName();
        String featureName = uniqueFeatureName();
        String configName = uniqueConfigName();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations")
            .then()
                .statusCode(200);
    }
}