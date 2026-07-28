package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    private final String baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActive() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String feature1 = "Feature1_" + UUID.randomUUID().toString();
        String feature2 = "Feature2_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post(baseURI + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post(baseURI + "/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(500);

        given().when().get(baseURI + "/products/" + productName + "/configurations/" + configName).then().body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceFeatureActive() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String feature1 = "Feature1_" + UUID.randomUUID().toString();
        String feature2 = "Feature2_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post(baseURI + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post(baseURI + "/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given().when().get(baseURI + "/products/" + productName + "/configurations/" + configName).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedFeatureActive() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String feature1 = "Feature1_" + UUID.randomUUID().toString();
        String feature2 = "Feature2_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post(baseURI + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post(baseURI + "/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(lessThan(300));

        given().when().get(baseURI + "/products/" + productName + "/configurations/" + configName).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testGetConstraintType() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String feature1 = "Feature1_" + UUID.randomUUID().toString();
        String feature2 = "Feature2_" + UUID.randomUUID().toString();

        given().when().post(baseURI + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post(baseURI + "/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().get(baseURI + "/products/" + productName).then().body("constraints[0].type", equalTo("excludes"));
    }

    @Test(timeout = 60000)
    public void testGetConstraintSourceFeatureName() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String feature1 = "Feature1_" + UUID.randomUUID().toString();
        String feature2 = "Feature2_" + UUID.randomUUID().toString();

        given().when().post(baseURI + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post(baseURI + "/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().get(baseURI + "/products/" + productName).then().body("constraints[0].sourceFeatureName", equalTo(feature1));
    }

    @Test(timeout = 60000)
    public void testGetConstraintExcludedFeatureName() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String feature1 = "Feature1_" + UUID.randomUUID().toString();
        String feature2 = "Feature2_" + UUID.randomUUID().toString();

        given().when().post(baseURI + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post(baseURI + "/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2).when().post(baseURI + "/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().get(baseURI + "/products/" + productName).then().body("constraints[0].excludedFeatureName", equalTo(feature2));
    }
}