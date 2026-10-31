package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_SucceedsReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_DuplicateCausesServerError() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Initial").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Duplicate attempt").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_SucceedsRemovesFromConfigurationsReturns204() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "To be removed").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NonExistingFeatureReturns500() {
        String product = "prod-" + UUID.randomUUID();
        String missingFeature = "missing-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, missingFeature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_SucceedsReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String required = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "source desc").when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "required desc").when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_BadProductReturns500() {
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", "Desktop-PC").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_SucceedsReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "exc-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "source").when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "excluded").when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_BadProductReturns500() {
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only").when().post("/products/{productName}/constraints/excludes", "Workstation-Z8").then().statusCode(500);
    }
}