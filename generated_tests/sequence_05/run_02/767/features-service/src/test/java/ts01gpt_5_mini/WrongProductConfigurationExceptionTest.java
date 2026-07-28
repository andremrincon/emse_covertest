package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void init() {
        String uri = System.getProperty("api.base");
        if (uri == null || uri.isEmpty()) {
            uri = System.getenv("API_BASE");
        }
        if (uri == null || uri.isEmpty()) {
            uri = "http://localhost:8080";
        }
        RestAssured.baseURI = uri;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersWrongProductConfigurationException() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        String problematicFeatureName = "new-feature";
        Response resp = given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", problematicFeatureName)
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}");
        resp.then().statusCode(500);
    }
}