package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationValidReturns201() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureName = "TestFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationInvalidReturnsError() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureName = "NonExistentFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureValidReturns204() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureName = "TestFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureInvalidReturnsError() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureName = "NonExistentFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }
}