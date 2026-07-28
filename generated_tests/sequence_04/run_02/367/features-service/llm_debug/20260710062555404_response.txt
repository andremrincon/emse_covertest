package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_success() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "A useful feature").when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_duplicateShouldReturn500() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "featdup-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "first").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "second").when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_withConfigurations_shouldReturn204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "featcfg-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "to be deleted").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_noConfigurations_shouldReturn204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-nocfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "standalone").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_success() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("requiredFeature", "req-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_success() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("excludedFeature", "excl-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }
}