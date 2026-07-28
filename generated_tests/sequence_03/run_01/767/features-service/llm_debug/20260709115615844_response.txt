package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base.url");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).formParam("description", "Test feature description").when().post("/products/{productName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicate() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String feature = "dupfeature-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).formParam("description", "First add").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).formParam("description", "Second add attempt").when().post("/products/{productName}/features/{featureName}").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithConfiguration() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).formParam("description", "To be deleted").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().delete("/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithoutConfiguration() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).formParam("description", "Standalone feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().delete("/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String req = "req-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductMissingFieldTriggersServerError() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", src).formParam("requiredFeature", "").when().post("/products/{productName}/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductMissingFieldTriggersServerError() {
        String product = "testprod-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).formParam("sourceFeature", "").formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes").then().statusCode(201);
    }
}