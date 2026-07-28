package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void createFeature_coversSetNameAndSetProduct() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createFeatureWithDescription_coversSetNameAndSetProduct() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when()
            .contentType(ContentType.URLENC)
            .formParam("description", "A test feature description")
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeatures_coversGetProduct() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeature_coversSetNameOnUpdate() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .put("/products/" + productName + "/features/" + featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_triggersEquals() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "C-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addMultipleFeaturesToConfiguration_triggersEqualsDifferentNames() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "F1-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "F2-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "C-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addSameFeatureToConfigurationTwice_triggersEqualsSameInstance() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "C-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void featuresFromDifferentProducts_triggersEqualsDifferentProducts() {
        String productName1 = "P1-" + UUID.randomUUID().toString().substring(0, 8);
        String productName2 = "P2-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);
        String configName1 = "C1-" + UUID.randomUUID().toString().substring(0, 8);
        String configName2 = "C2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName1 + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2 + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName1 + "/configurations/" + configName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2 + "/configurations/" + configName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName1 + "/configurations/" + configName1 + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName2 + "/configurations/" + configName2 + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_triggersEqualsOnRemoval() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "C-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromProduct_triggersEquals() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeatures_triggersEqualsAndGetProduct() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "C-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getProductByName_triggersEqualsOnFeatureCollection() {
        String productName = "P-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "F-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName)
            .then().statusCode(200);
    }
}