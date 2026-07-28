package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithoutDerivedFeatures() {
        String product = "Prod-" + UUID.randomUUID().toString();
        String featureA = "FeatA-" + UUID.randomUUID().toString();
        String config = "Conf-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + product).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + product + "/features/" + featureA).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + product + "/configurations/" + config + "/features/" + featureA).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithDerivedFeatures() {
        String product = "Prod-" + UUID.randomUUID().toString();
        String featureA = "FeatA-" + UUID.randomUUID().toString();
        String featureB = "FeatB-" + UUID.randomUUID().toString();
        String config = "Conf-" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + product).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + product + "/features/" + featureA).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + product + "/features/" + featureB).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/" + product + "/constraints/requires").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + product + "/configurations/" + config + "/features/" + featureA).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + product + "/configurations/" + config + "/features").then().statusCode(200);
    }
}