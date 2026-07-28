package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeatureCoversSetNameAndSetProduct() {
        String productName = "Product-SetName-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-SetName-" + System.currentTimeMillis();

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureCoversSetName() {
        String productName = "Product-Update-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Update-" + System.currentTimeMillis();
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
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesCoversGetProduct() {
        String productName = "Product-Get-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Get-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}/features")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testCreateDuplicateFeatureCoversEqualsSameNameAndProduct() {
        String productName = "Product-Dup-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Dup-" + System.currentTimeMillis();
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
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureWithSameNameDifferentProductCoversEqualsDifferentProduct() {
        String productName1 = "Product1-" + System.currentTimeMillis();
        String productName2 = "Product2-" + System.currentTimeMillis();
        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));

        String featureName = "SharedFeature-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName1)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName2)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateMultipleFeaturesCoversEqualsDifferentNames() {
        String productName = "Product-Multi-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName1 = "Feature1-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        String featureName2 = "Feature2-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCoversGetProduct() {
        String productName = "Product-Config-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Config-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        String configName = "Config-" + System.currentTimeMillis();
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
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesCoversGetProduct() {
        String productName = "Product-GetConfig-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-GetConfig-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        String configName = "Config-Get-" + System.currentTimeMillis();
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

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureCoversSetProduct() {
        String productName = "Product-Delete-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Delete-" + System.currentTimeMillis();
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
    public void testUpdateFeatureWithDescriptionCoversSetName() {
        String productName = "Product-Desc-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Desc-" + System.currentTimeMillis();
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
            .formParam("description", "New description for feature")
            .when()
            .put("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(200)
            .body("description", equalTo("New description for feature"));
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesAfterMultipleAddsCoversEquals() {
        String productName = "Product-MultiAdd-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName1 = "Feature-A-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        String featureName2 = "Feature-B-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}/features")
            .then()
            .statusCode(200)
            .body("size()", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationCoversGetProduct() {
        String productName = "Product-Remove-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Remove-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        String configName = "Config-Remove-" + System.currentTimeMillis();
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

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(204);
    }
}