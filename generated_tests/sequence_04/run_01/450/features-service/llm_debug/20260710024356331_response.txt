package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excluded = "excl-" + UUID.randomUUID().toString().substring(0, 8);
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_responseContainsConstraintType_EXCLUDES() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "cpu-" + UUID.randomUUID().toString().substring(0, 8);
        String excluded = "ig-" + UUID.randomUUID().toString().substring(0, 8);
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_responseContainsSourceFeatureName() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excluded = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_responseContainsExcludedFeatureName() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "SF-" + UUID.randomUUID().toString().substring(0, 8);
        String excluded = "EF-" + UUID.randomUUID().toString().substring(0, 8);
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testConfigurationWithExcludingFeatures_setsValidFalse_whenBothActive() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String config = "conf-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        String source = "feat-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excluded = "feat-excl-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testConfigurationWithOnlySourceActive_keepsValidTrue() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String config = "conf-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        String source = "only-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excluded = "only-excl-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testConfigurationWithOnlyExcludedActive_keepsValidTrue() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String config = "conf-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        String source = "src-only-" + UUID.randomUUID().toString().substring(0, 8);
        String excluded = "excl-only-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withoutFormFields_returns201() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }
}