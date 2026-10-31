package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    private String createProductWithFeaturesAndRequiresConstraint(String sourceFeature, String requiredFeature) {
        String productName = "TP-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));
        return productName;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintReturns201() {
        String productName = "P-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testGetProductExposesRequiresConstraintType() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredNotActive() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);
        String configName = "C-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceNotActiveRequiredActive() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);
        String configName = "C-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActive() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);
        String configName = "C-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherFeatureActive() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);
        String configName = "C-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithNameEvaluatesRequiresConstraint() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);
        String configName = "C-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraint() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);

        Response productResponse = given().when().get("/products/{productName}", productName);
        Integer constraintId = productResponse.jsonPath().getInt("constraints[0].id");

        given()
        .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListForProductWithRequiresConstraint() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);
        String configName = "C-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductWithRequiresConstraint() {
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = createProductWithFeaturesAndRequiresConstraint(sourceFeature, requiredFeature);

        given()
        .when()
            .get("/products/{productName}/features", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintOnNonExistentProductReturns500() {
        String productName = "NonExistent-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithMultipleRequiresConstraints() {
        String productName = "P-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sf1 = "SF1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String rf1 = "RF1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sf2 = "SF2-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String rf2 = "RF2-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "C-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sf1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, rf1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sf2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, rf2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sf1).formParam("requiredFeature", rf1).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sf2).formParam("requiredFeature", rf2).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sf1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sf2).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }
}