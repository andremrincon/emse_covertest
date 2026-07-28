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

    @Before
    public void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ?
            System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSetNameViaCreateFeature() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        Response response = given()
            .formParam("description", "Test feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetNameViaUpdateFeature() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        given()
            .formParam("description", "Original description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .formParam("description", "Updated description")
            .when()
            .put("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetProductViaCreateFeature() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        Response response = given()
            .formParam("description", "Feature with product")
            .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductViaRetrieveFeatures() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        given()
            .formParam("description", "Test feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/products/" + productName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualsViaDuplicateFeatureCreation() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "DuplicateFeature-" + UUID.randomUUID();
        given()
            .formParam("description", "First creation")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .formParam("description", "Duplicate attempt")
            .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEqualsViaSameFeatureNameDifferentProducts() {
        String productName1 = "Product1-" + UUID.randomUUID();
        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));

        String productName2 = "Product2-" + UUID.randomUUID();
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));

        String featureName = "SharedFeature-" + UUID.randomUUID();
        given()
            .formParam("description", "Feature in product 1")
            .when()
            .post("/products/" + productName1 + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .formParam("description", "Feature in product 2")
            .when()
            .post("/products/" + productName2 + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEqualsViaAddFeatureToConfiguration() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        given()
            .formParam("description", "Test feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID();
        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductViaGetConfigurationFeatures() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        given()
            .formParam("description", "Test feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID();
        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetNameViaCreateFeatureWithDescription() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        Response response = given()
            .formParam("description", "Detailed feature description for testing")
            .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetProductViaMultipleFeatures() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName1 = "Feature1-" + UUID.randomUUID();
        given()
            .formParam("description", "First feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName1)
            .then().statusCode(lessThan(300));

        String featureName2 = "Feature2-" + UUID.randomUUID();
        Response response = given()
            .formParam("description", "Second feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName2);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductViaGetProductDetails() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        given()
            .formParam("description", "Test feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/products/" + productName);

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualsViaRemoveFeatureFromConfiguration() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        given()
            .formParam("description", "Test feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID();
        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        Response response = given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(204);
    }
}