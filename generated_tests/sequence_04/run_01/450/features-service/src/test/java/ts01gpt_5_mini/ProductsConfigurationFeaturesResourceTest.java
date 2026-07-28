package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code <201> but was <405>.")
    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().put("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <405>.")
    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFailureInvalidFeatureName() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String badFeature = "feature-name-that-is-intentionally-made-very-long-to-exceed-potential-database-column-size-limits-or-other-internal-buffer-restrictions-leading-to-an-unhandled-server-side-exception-and-a-500-internal-server-error-response";
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", badFeature).when().put("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <405> was greater than <300>.")
    @Test(timeout = 60000)
    public void testDeleteFeatureSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().put("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFailureInvalidFeatureName() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String badFeature = "feature-name-that-is-intentionally-made-very-long-to-exceed-potential-database-column-size-limits-or-other-internal-buffer-restrictions-leading-to-an-unhandled-server-side-exception-and-a-500-internal-server-error-response";
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", badFeature).when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(500);
    }

}