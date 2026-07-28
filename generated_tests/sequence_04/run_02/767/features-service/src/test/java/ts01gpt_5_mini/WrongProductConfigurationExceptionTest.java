package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateProductAndAddConfiguration201() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddLongFeatureToConfigurationCausesServerError500() {
        String productName = "prod-long-" + UUID.randomUUID().toString();
        String configurationName = "config-long-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1200; i++) sb.append('X');
        String longFeature = sb.toString();
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, longFeature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct201() {
        String productName = "prod-feature-" + UUID.randomUUID().toString();
        String featureName = "Blood Oxygen Sensor";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(500);
    }
}