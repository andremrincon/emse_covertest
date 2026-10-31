package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testGetFeaturesAfterAddingFeatureCoversSetNameSetProduct() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescriptionCoversSetName() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesWithMultipleFeaturesCoversEqualsDifferentNames() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "tf1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "tf2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, feature1)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, feature2)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCoversEquals() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "tc-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesCoversEqualsAndGetProduct() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "tc-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
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
    public void testDeleteFeatureCoversEquals() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationCoversEquals() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "tc-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
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
    public void testGetProductCoversSetNameSetProductGetProduct() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigTwiceCoversEqualsSameName() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "tc-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetailsCoversEqualsAndGetProduct() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "tc-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsCoversEquals() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "tc-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                  productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescriptionCoversSetNameSetProduct() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description for feature")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }
}