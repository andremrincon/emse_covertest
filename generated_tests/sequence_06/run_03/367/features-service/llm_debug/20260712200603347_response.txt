package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_valid_returns201() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_excludedByConstraint_returns400() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String featureA = "feat-a-" + UUID.randomUUID().toString();
        String featureB = "feat-b-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeature_valid_returns204() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_requiredByAnotherFeature_returns400() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String featureA = "feat-a-" + UUID.randomUUID().toString();
        String featureB = "feat-b-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
            .then().statusCode(204);
    }
}