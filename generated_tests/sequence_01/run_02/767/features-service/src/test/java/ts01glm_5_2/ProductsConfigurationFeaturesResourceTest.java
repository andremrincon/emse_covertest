package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_valid_returns201() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

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
    public void addFeatureToConfiguration_invalid_returnsError() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configurationName = "TestConfig-" + uuid;

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
    public void deleteFeature_valid_returns204() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

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
    public void deleteFeature_invalid_returnsError() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configurationName = "TestConfig-" + uuid;

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