package ts01qwen3_7_plus_2;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Test(timeout = 60000)
    public void testGetConfiguration() {
        String productName = "Prod_" + UUID.randomUUID();
        String featureName = "Feat_" + UUID.randomUUID();
        String configName = "Conf_" + UUID.randomUUID();
        String baseUrl = System.getProperty("base.url", "http://localhost:8080");

        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "Prod_" + UUID.randomUUID();
        String featureName = "Feat_" + UUID.randomUUID();
        String configName = "Conf_" + UUID.randomUUID();
        String baseUrl = System.getProperty("base.url", "http://localhost:8080");

        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConfiguration() {
        String productName = "Prod_" + UUID.randomUUID();
        String featureName = "Feat_" + UUID.randomUUID();
        String configName = "Conf_" + UUID.randomUUID();
        String baseUrl = System.getProperty("base.url", "http://localhost:8080");

        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().delete("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(204);
    }
}