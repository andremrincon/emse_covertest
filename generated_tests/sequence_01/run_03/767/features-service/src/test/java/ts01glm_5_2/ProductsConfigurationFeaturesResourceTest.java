package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_success_returns204() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_featureNotPresent_returnsError() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_nonExistentProduct_returnsError() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "NonExistentProd-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(greaterThanOrEqualTo(400));
    }
}