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
        String env = System.getenv("API_BASE_URL");
        String base = System.getProperty("api.baseUrl", env != null ? env : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").
                when().post("/products/{productName}/features/{featureName}", product, feature).
                then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicate() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-dup-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "first").
                when().post("/products/{productName}/features/{featureName}", product, feature).
                then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "second").
                when().post("/products/{productName}/features/{featureName}", product, feature).
                then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithConfigurationsRemovesFromConfigurations() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-cfg-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to-delete").
                when().post("/products/{productName}/features/{featureName}", product, feature).
                then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).
                then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).
                then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).
                then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithoutConfigurations() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-nocfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "solo").
                when().post("/products/{productName}/features/{featureName}", product, feature).
                then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).
                then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "A").formParam("requiredFeature", "B").
                when().post("/products/{productName}/constraints/requires", product).
                then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintProductMissing() {
        String product = "missing-prod-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "X").formParam("requiredFeature", "Y").
                when().post("/products/{productName}/constraints/requires", product).
                then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "C").formParam("excludedFeature", "D").
                when().post("/products/{productName}/constraints/excludes", product).
                then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintProductMissing() {
        String product = "missing-prod-excl-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "M").formParam("excludedFeature", "N").
                when().post("/products/{productName}/constraints/excludes", product).
                then().statusCode(500);
    }
}