package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationSuccessfully() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        String configurationName = "config-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addDuplicateFeatureToConfigurationThrowsException() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        String configurationName = "config-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationSuccessfully() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        String configurationName = "config-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(204);
    }
}