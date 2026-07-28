package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class ConfigurationEvaluatorTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoConstraints_1() {
        String baseUrl = getBaseUrl();
        String productName = "Prod1-" + UUID.randomUUID();
        String featureName = "Feat1";
        String configName = "Config1";

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraint_2() {
        String baseUrl = getBaseUrl();
        String productName = "Prod2-" + UUID.randomUUID();
        String sourceFeature = "SourceFeat";
        String requiredFeature = "RequiredFeat";
        String configName = "Config2";

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraint_3() {
        String baseUrl = getBaseUrl();
        String productName = "Prod3-" + UUID.randomUUID();
        String sourceFeature = "SourceFeat3";
        String excludedFeature = "ExcludedFeat3";
        String configName = "Config3";

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }
}