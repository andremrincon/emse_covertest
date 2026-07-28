package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_validFeature_returns201() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_nonExistentFeature_returns400() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "nonexistent-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeature_validFeature_returns204() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_nonExistentFeature_returns400() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "nonexistent-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(500);
    }
}