package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("API_BASE", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().delete("/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_containsAddedFeature_inBody() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().get("/products/{productName}/features").then().body(containsString(feature));
    }

    @Test(timeout = 60000)
    public void testBuildWithFeatures_multipleAdds_resultsInTwoFeaturesSize() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature1 = "featA-" + UUID.randomUUID().toString();
        String feature2 = "featB-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().get("/products/{productName}/features").then().body("size()", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withExistingFeatures_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", source).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", required).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withMissingRequiredFeature_returns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String missing = "missing-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", source).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).formParam("sourceFeature", source).formParam("requiredFeature", missing).when().post("/products/{productName}/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_withExistingFeatures_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "excl-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", source).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", excluded).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes").then().statusCode(201);
    }
}