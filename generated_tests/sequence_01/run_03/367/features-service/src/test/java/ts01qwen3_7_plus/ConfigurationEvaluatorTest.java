package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class ConfigurationEvaluatorTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoConstraints() {
        String productName = "Prod1_" + UUID.randomUUID().toString();
        String featureName = "Feat1_" + UUID.randomUUID().toString();
        String configName = "Config1_" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl)
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationExcludesConstraintViolated() {
        String productName = "Prod2_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Config2_" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given().baseUri(baseUrl)
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature2)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given().baseUri(baseUrl)
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationRequiresConstraintTriggered() {
        String productName = "Prod3_" + UUID.randomUUID().toString();
        String sourceFeature = "Source_" + UUID.randomUUID().toString();
        String requiredFeature = "Required_" + UUID.randomUUID().toString();
        String configName = "Config3_" + UUID.randomUUID().toString();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl)
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl)
                .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
                .then()
                .statusCode(200)
                .body("$", hasItem(requiredFeature));
    }
}