package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateFeatureExercisesSetNameAndSetProduct() {
        String productName = "Product-Create-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Create-" + System.currentTimeMillis();

        Response response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureExercisesSetName() {
        String productName = "Product-Update-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Update-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Original description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        Response response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Updated description")
        .when()
            .put("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(200);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetFeaturesExercisesGetProduct() {
        String productName = "Product-Get-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Get-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        Response response = given()
        .when()
            .get("/products/" + productName + "/features");

        response.then().statusCode(200);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExercisesEquals() {
        String productName = "Product-Config-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Config-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + System.currentTimeMillis();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(201);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesExercisesGetProductAndEquals() {
        String productName = "Product-GetConfig-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-GetConfig-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        String configName = "Config-GetConfig-" + System.currentTimeMillis();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        Response response = given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateDuplicateFeatureExercisesEqualsBranches() {
        String productName = "Product-Duplicate-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-Duplicate-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "First feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        Response response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Duplicate feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(500);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateSameFeatureNameInDifferentProductsExercisesEquals() {
        String productName1 = "Product1-Same-" + System.currentTimeMillis();
        String productName2 = "Product2-Same-" + System.currentTimeMillis();
        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));

        String featureName = "Feature-Same-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Feature in product 1")
        .when()
            .post("/products/" + productName1 + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        Response response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Feature in product 2")
        .when()
            .post("/products/" + productName2 + "/features/" + featureName);

        response.then().statusCode(201);

        given().when().delete("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName2).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateMultipleFeaturesInSameProductExercisesEquals() {
        String productName = "Product-Multiple-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName1 = "Feature1-Multiple-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "First feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName1)
        .then().statusCode(lessThan(300));

        String featureName2 = "Feature2-Multiple-" + System.currentTimeMillis();
        Response response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Second feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName2);

        response.then().statusCode(201);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureToConfigurationExercisesEquals() {
        String productName = "Product-DupConfig-" + System.currentTimeMillis();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-DupConfig-" + System.currentTimeMillis();
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        String configName = "Config-DupConfig-" + System.currentTimeMillis();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        Response response = given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(500);

        given().when().delete("/products/" + productName).then().statusCode(lessThan(300));
    }
}