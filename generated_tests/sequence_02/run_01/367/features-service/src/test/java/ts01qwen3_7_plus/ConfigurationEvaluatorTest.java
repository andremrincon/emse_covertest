package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoConstraints() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        String productName = "Prod1_" + UUID.randomUUID().toString();
        String featureName = "Feat1";
        String configName = "Conf1";

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraint() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        String productName = "Prod2_" + UUID.randomUUID().toString();
        String featureA = "FeatA";
        String featureB = "FeatB";
        String configName = "Conf2";

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraint() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        String productName = "Prod3_" + UUID.randomUUID().toString();
        String featureA = "FeatA";
        String featureB = "FeatB";
        String configName = "Conf3";

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(500);

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }
}