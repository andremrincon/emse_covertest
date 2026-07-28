package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_valid_returns201() {
        String productName = "test-product-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_nonExistentFeature_returnsError() {
        String productName = "test-product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "non-existent-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeature_valid_returns204() {
        String productName = "test-product-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_nonExistentFeature_returnsError() {
        String productName = "test-product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "non-existent-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_nonExistentProduct_returnsError() {
        String productName = "non-existent-product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeature_nonExistentProduct_returnsError() {
        String productName = "non-existent-product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
                .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }
}