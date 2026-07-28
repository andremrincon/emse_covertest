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
        RestAssured.baseURI = System.getenv("BASE_URL") != null ?
                System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSetNameViaPostFeature() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        Response response = given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetNameViaPutFeature() {
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
    public void testSetProductViaPostFeature() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID();
        Response response = given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductViaGetFeatures() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID();
        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then().statusCode(lessThan(300));

        Response response = given()
                .when()
                .get("/products/" + productName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualsViaAddFeatureToConfiguration() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID();
        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then().statusCode(lessThan(300));
        String configName = "Config-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given()
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEqualsViaSameFeatureNameInDifferentProducts() {
        String productName1 = "Product1-" + UUID.randomUUID();
        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        String productName2 = "Product2-" + UUID.randomUUID();
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));

        String featureName = "SharedFeature-" + UUID.randomUUID();
        Response response1 = given()
                .formParam("description", "Description 1")
                .when()
                .post("/products/" + productName1 + "/features/" + featureName);

        response1.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEqualsViaGetConfigurationFeatures() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID();
        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then().statusCode(lessThan(300));
        String configName = "Config-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
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
    public void testEqualsViaDeleteFeatureFromConfiguration() {
        String productName = "Product-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID();
        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then().statusCode(lessThan(300));
        String configName = "Config-" + UUID.randomUUID();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
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