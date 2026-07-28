package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class ProductsConfigurationsServiceTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .get(baseUrl + "/products/" + productName + "/configurations")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProductEmpty() {
        String productName = "Product-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));

        given()
            .when()
            .get(baseUrl + "/products/" + productName + "/configurations")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNamesEmpty() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .get(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationDuplicate() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(anyOf(equalTo(400), equalTo(409), equalTo(500)));
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationNotActive() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }
}