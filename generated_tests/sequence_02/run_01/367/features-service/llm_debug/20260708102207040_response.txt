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
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private String uniqueName() {
        return "test-" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureExercisesWithNameAndSetProduct() {
        String productName = uniqueName();
        String featureName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureWithDescriptionExercisesSetProductAndSetName() {
        String productName = uniqueName();
        String featureName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "A test feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureExercisesSetName() {
        String productName = uniqueName();
        String featureName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description value")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesExercisesGetProduct() {
        String productName = uniqueName();
        String featureName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExercisesEqualsTrueBranch() {
        String productName = uniqueName();
        String featureName = uniqueName();
        String configName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationTwiceExercisesEqualsSameReference() {
        String productName = uniqueName();
        String featureName = uniqueName();
        String configName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddDifferentFeaturesToConfigurationExercisesEqualsDifferentName() {
        String productName = uniqueName();
        String feature1 = uniqueName();
        String feature2 = uniqueName();
        String configName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSameFeatureNameDifferentProductsExercisesEqualsSameNameDifferentProduct() {
        String product1 = uniqueName();
        String product2 = uniqueName();
        String featureName = uniqueName();
        String configName = uniqueName();

        given().when().post("/products/" + product1).then().statusCode(lessThan(300));
        given().when().post("/products/" + product2).then().statusCode(lessThan(300));
        given().when().post("/products/" + product1 + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + product2 + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + product1 + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + product1 + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesExercisesEqualsAndGetProduct() {
        String productName = uniqueName();
        String featureName = uniqueName();
        String configName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationExercisesEquals() {
        String productName = uniqueName();
        String featureName = uniqueName();
        String configName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductExercisesSetProductNull() {
        String productName = uniqueName();
        String featureName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testUpdateThenGetFeatureExercisesSetNameAndGetProductTogether() {
        String productName = uniqueName();
        String featureName = uniqueName();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Revised description after update")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given().when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }
}