package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Desc A").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicateThrows() {
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "First").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Second").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithoutConfigurations() {
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "ToDelete").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithConfigurationRemovesFeature() {
        String product = "product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        String config = "config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Linked").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String product = "product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("requiredFeature", "req-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String product = "product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("excludedFeature", "excl-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }
}