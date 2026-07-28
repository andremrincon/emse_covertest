package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    private String productName;
    private String sourceFeature;
    private String excludedFeature;
    private String configName;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
        productName = "Product-" + UUID.randomUUID().toString();
        sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();
        configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraint() {
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200)
            .body("constraints.type", hasItem("excludes"));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActive() {
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceFeatureActive() {
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedFeatureActive() {
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoFeaturesActive() {
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", equalTo(true));
    }
}