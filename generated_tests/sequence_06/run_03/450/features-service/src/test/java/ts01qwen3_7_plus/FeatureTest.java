package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + System.currentTimeMillis();
        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/features", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        String configName = "Config-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        String configName = "Config-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String feature1 = "Feature1-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        String feature2 = "Feature2-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String feature1 = "Feature1-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        String feature2 = "Feature2-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then()
            .statusCode(201);
    }
}