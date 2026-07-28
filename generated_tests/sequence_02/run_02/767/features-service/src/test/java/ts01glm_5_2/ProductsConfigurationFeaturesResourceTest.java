package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturnsNoContentWhenFeatureExists() {
        String uuid = UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureName = "TestFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturnsErrorWhenFeatureNotPresent() {
        String uuid = UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureName = "NonExistentFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(anyOf(is(400), is(500)));
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromNonExistentConfigurationReturnsError() {
        String uuid = UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String configurationName = "NonExistentConfig-" + uuid;
        String featureName = "TestFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(anyOf(is(400), is(404), is(500)));
    }
}