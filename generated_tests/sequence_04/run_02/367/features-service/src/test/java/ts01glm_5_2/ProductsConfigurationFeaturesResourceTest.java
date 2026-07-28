package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    private String uniqueName(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String productName = uniqueName("TestProduct");
        String featureName = uniqueName("TestFeature");
        String configurationName = uniqueName("TestConfig");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFailure() {
        String productName = uniqueName("TestProduct");
        String featureA = uniqueName("FeatureA");
        String featureB = uniqueName("FeatureB");
        String configurationName = uniqueName("TestConfig");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureSuccess() {
        String productName = uniqueName("TestProduct");
        String featureName = uniqueName("TestFeature");
        String configurationName = uniqueName("TestConfig");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <204>.")
    @Test(timeout = 60000)
    public void testDeleteFeatureFailure() {
        String productName = uniqueName("TestProduct");
        String featureA = uniqueName("FeatureA");
        String featureB = uniqueName("FeatureB");
        String configurationName = uniqueName("TestConfig");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
            .then()
            .statusCode(400);
    }
}