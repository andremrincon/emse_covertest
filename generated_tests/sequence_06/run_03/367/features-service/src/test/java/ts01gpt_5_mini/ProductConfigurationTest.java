package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        RestAssured.baseURI = base != null ? base : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGetConfiguration_includesAvailableFeatures_whenProductHasFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureA = "featureA-" + UUID.randomUUID().toString();
        String featureB = "featureB-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfiguration_withNoProductFeatures_returnsConfigurationNameInBody() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        act.then().statusCode(200).body("configurationName", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_activatesFeature_and_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        act.then().statusCode(201);
    }
}