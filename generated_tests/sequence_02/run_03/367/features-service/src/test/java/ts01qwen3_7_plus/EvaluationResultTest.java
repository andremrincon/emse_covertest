package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @Test(timeout = 60000)
    public void testEvaluationResultInstantiation() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080");
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }
}