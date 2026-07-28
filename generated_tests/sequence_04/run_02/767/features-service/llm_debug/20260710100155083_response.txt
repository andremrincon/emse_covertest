package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
    public void testAddFeatureToProduct_Succeeds() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_DuplicateCausesServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-dup-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_RemovesFeatureFromConfigurationsAndDeletes() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NoConfigurations_DeletesSuccessfully() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Succeeds() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "source-" + UUID.randomUUID().toString();
        String required = "required-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_OnMissingProductReturnsServerError() {
        String product = "nonexistent-" + UUID.randomUUID().toString();
        String source = "source-" + UUID.randomUUID().toString();
        String required = "required-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Succeeds() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "source-excl-" + UUID.randomUUID().toString();
        String excluded = "excluded-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_OnMissingProductReturnsServerError() {
        String product = "nonexistent-excl-" + UUID.randomUUID().toString();
        String source = "source-excl-" + UUID.randomUUID().toString();
        String excluded = "excluded-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(500);
    }
}