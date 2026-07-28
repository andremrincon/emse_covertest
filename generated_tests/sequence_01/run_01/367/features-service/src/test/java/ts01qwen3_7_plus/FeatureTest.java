package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateProductAndFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        Response response = given().when().post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("description", "Updated description")
                .when().put("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName);

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateSameFeatureNameInDifferentProducts() {
        String productName1 = "Product1-" + UUID.randomUUID().toString();
        String productName2 = "Product2-" + UUID.randomUUID().toString();
        String featureName = "SharedFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName1 + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2 + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products");

        response.then().statusCode(200);
    }
}