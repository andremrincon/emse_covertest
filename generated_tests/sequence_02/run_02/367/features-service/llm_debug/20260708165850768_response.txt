package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void deleteFeatureSuccessReturns204() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureWithRequiresConstraintViolationReturns400() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String sourceFeature = "source-feat-" + UUID.randomUUID().toString();
        String requiredFeature = "required-feat-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureNotPresentInConfigurationReturns400() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "nonexistent-feat-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(500);
    }
}