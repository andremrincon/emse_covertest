package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addExistingProductFeatureToConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addNonExistingFeatureToConfiguration_returns500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String missingFeature = "missing-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, missingFeature);
        act.then().statusCode(500);
    }
}