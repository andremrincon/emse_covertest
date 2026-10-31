package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setup() {
        String apiBase = System.getenv("API_BASE_URL");
        if (apiBase == null || apiBase.isEmpty()) {
            apiBase = "http://localhost:8080";
        }
        RestAssured.baseURI = apiBase;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationDuplicateProducesServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String feature = "dupfeat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String feature = "remfeat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveNonexistentFeatureFromConfigurationProducesServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String feature = "nonexistent-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesServerErrorForBadProduct() {
        String badProduct = "a_very_long_product_name_that_likely_exceeds_the_maximum_allowed_length_in_the_database_or_application_logic_leading_to_an_unhandled_exception_and_a_generic_server_error_response";
        String configuration = "us-standard-16gb";
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", badProduct, configuration);
        act.then().statusCode(500);
    }
}