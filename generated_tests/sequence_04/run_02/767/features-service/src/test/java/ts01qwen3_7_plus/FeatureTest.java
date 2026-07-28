package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateFeatureSetsProductAndName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureSetsName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String newDescription = "Updated description";

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

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .formParam("description", newDescription)
            .when()
            .put("/products/{productName}/features/{featureName}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesReturnsProductAssociation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

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

        Response response = given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationTriggersEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

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
            .pathParam("configurationName", configName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureToConfigurationTriggersEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

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
            .pathParam("configurationName", configName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}");

        response.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesTriggersEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName1 = "TestFeature1-" + UUID.randomUUID().toString();
        String featureName2 = "TestFeature2-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationTriggersEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

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
            .pathParam("configurationName", configName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}");

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureNameTriggersEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String updatedDescription = "New description " + UUID.randomUUID().toString();

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

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .formParam("description", updatedDescription)
            .when()
            .put("/products/{productName}/features/{featureName}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesTriggersGetProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

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

        Response response = given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateMultipleFeaturesTriggersSetProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName1 = "TestFeature1-" + UUID.randomUUID().toString();
        String featureName2 = "TestFeature2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName2}");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureTriggersEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

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

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .delete("/products/{productName}/features/{featureName}");

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configName = "TestConfig-" + UUID.randomUUID().toString();

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
            .pathParam("configurationName", configName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}");

        response.then().statusCode(201);
    }
}