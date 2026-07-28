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
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    private String uniqueName(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureWithDescription() {
        String productName = uniqueName("Product");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = uniqueName("Product");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = uniqueName("Product");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
        .when()
            .put("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = uniqueName("Product");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = uniqueName("Product");
        String configurationName = uniqueName("Config");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSecondFeatureToConfiguration() {
        String productName = uniqueName("Product");
        String configurationName = uniqueName("Config");
        String featureName1 = uniqueName("Feature1");
        String featureName2 = uniqueName("Feature2");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName1).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName2)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = uniqueName("Product");
        String configurationName = uniqueName("Config");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/configurations/" + configurationName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = uniqueName("Product");
        String configurationName = uniqueName("Config");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = uniqueName("Product");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        String productName = uniqueName("Product");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct() {
        String productName = uniqueName("Product");
        String configurationName = uniqueName("Config");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationByName() {
        String productName = uniqueName("Product");
        String configurationName = uniqueName("Config");
        String featureName = uniqueName("Feature");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/configurations/" + configurationName)
        .then()
            .statusCode(200);
    }
}