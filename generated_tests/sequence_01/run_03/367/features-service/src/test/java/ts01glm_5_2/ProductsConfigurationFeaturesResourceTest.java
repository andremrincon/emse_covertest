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
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns204() {
        String productName = "prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromNonExistentConfigurationReturns500() {
        String productName = "prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(500);
    }
}