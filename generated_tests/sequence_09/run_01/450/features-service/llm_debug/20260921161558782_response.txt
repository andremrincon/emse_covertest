package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private static String base() {
        String b = System.getProperty("api.base.url");
        if (b != null && !b.isEmpty()) return b;
        b = System.getenv("API_BASE_URL");
        if (b != null && !b.isEmpty()) return b;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void createConfiguration_forExistingProduct_returns201() {
        String base = base();
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().baseUri(base).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(base).when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String base = base();
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().baseUri(base).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(base).contentType("application/x-www-form-urlencoded").formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationByName_returns200() {
        String base = base();
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().baseUri(base).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(base).when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200);
    }
}