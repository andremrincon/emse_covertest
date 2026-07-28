package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithoutDerivedFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature2)
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature1).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then().statusCode(200).body("$", hasItem(feature1));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithDerivedFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature1).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then().statusCode(200).body("$", hasItems(feature1, feature2));
    }
}