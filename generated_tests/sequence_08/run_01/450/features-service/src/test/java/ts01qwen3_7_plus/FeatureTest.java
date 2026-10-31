package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String productName;
    private String featureName;
    private String configName;

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        productName = "Product-" + UUID.randomUUID().toString();
        featureName = "Feature-" + UUID.randomUUID().toString();
        configName = "Config-" + UUID.randomUUID().toString();
    }

    @Test(timeout = 60000)
    public void testCreateProduct() {


        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));


        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));


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
    public void testGetFeaturesForProduct() {

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));


        given()
            .pathParam("productName", productName)
        .when()
            .get("/products/{productName}/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateConfiguration() {

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));


        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));


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
    public void testGetConfigurationFeatures() {

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));


        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));


        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .delete("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(204);
    }
}