package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActive() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(500);

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200).body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceFeatureActive() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedFeatureActive() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoFeaturesActive() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraint() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String feature1 = "Feat1_" + UUID.randomUUID().toString();
        String feature2 = "Feat2_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName).then().statusCode(200).body("constraints[0].type", equalTo("excludes"));
    }
}