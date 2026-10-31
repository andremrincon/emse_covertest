package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "prod-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
        .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicate() {
        String productName = "prod-dup-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "dup-feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("description", "First feature")
        .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Duplicate feature")
        .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithoutDescription() {
        String productName = "prod-nodesc-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "nodesc-feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithNoConfigurations() {
        String productName = "prod-del-nocfg-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "del-nocfg-feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithActiveConfiguration() {
        String productName = "prod-del-cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "del-cfg-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithMultipleActiveConfigurations() {
        String productName = "prod-del-mcfg-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "del-mcfg-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String config1 = "cfg1-" + UUID.randomUUID().toString().substring(0, 8);
        String config2 = "cfg2-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, config1)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, config1, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, config2)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, config2, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureNotFound() {
        String productName = "prod-notfound-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "prod-req-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "RAID-Controller-Card")
            .formParam("requiredFeature", "128GB-ECC-RAM")
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "prod-exc-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithExistingFeatures() {
        String productName = "prod-req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithExistingFeatures() {
        String productName = "prod-exc-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "exc-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-excl-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithConfigButNotActive() {
        String productName = "prod-del-cfgna-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "del-cfgna-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfgna-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .contentType(ContentType.URLENC)
        .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
                .statusCode(204);
    }
}