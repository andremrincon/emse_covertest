package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .delete("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameNotFound() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String nonExistentFeature = "NonExistent-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", nonExistentFeature)
            .formParam("requiredFeature", "any")
        .when()
            .post("/products/{productName}/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamedNotFound() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String nonExistentFeature = "NonExistent-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", nonExistentFeature)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(500);
    }
}