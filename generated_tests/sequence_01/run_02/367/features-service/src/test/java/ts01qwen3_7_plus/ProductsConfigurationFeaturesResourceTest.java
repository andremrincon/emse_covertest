package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Success() {
        String productName = "Product-Add-" + java.util.UUID.randomUUID().toString();
        String configurationName = "Config-Add-" + java.util.UUID.randomUUID().toString();
        String featureName = "Feature-Add-" + java.util.UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Failure() {
        String productName = "QuantumLeap-AI-Platform";
        String configurationName = "standard-gpu-cluster";
        String featureName = "feature-name-that-is-intentionally-made-extremely-long-to-exceed-any-reasonable-database-column-width-or-url-path-segment-limit-and-potentially-cause-an-unhandled-exception-or-a-buffer-overflow-somewhere-deep-in-the-application-stack-resulting-in-a-generic-five-hundred-internal-server-error-response-instead-of-a-more-graceful-four-hundred-bad-request-error";

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "Product-Del-" + java.util.UUID.randomUUID().toString();
        String configurationName = "Config-Del-" + java.util.UUID.randomUUID().toString();
        String featureName = "Feature-Del-" + java.util.UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
        .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String productName = "DataStreamer";
        String configurationName = "Standard";
        String featureName = "feature-name-that-is-intentionally-made-very-long-to-exceed-potential-database-column-size-limits-or-other-internal-buffer-restrictions-leading-to-an-unhandled-server-side-exception-and-a-500-internal-server-error-response";

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
        .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(500);
    }
}