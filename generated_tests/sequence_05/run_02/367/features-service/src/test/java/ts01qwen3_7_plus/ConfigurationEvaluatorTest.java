package ts01glm_5_2;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithDerivedFeatures() {
        String productName = "QuantumLeap-AI-Platform-" + UUID.randomUUID().toString();
        String feature1 = "distributed-training";
        String feature2 = "sso";
        String configName = "standard-gpu-cluster";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("requiredFeature", feature2).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithoutDerivedFeatures() {
        String productName = "P1-" + UUID.randomUUID().toString();
        String feature1 = "FeatA";
        String feature2 = "FeatB";
        String configName = "basic";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(201);
    }
}