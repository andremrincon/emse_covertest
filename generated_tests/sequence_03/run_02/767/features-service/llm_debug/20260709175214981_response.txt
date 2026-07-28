package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_bothActive_makesConfigurationInvalid() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "feature-src-" + UUID.randomUUID().toString();
        String exc = "feature-exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exc).then().statusCode(lessThan(300));
        String cfg = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", product, cfg).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, cfg, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, cfg, exc).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", exc).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, cfg).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_onlySourceActive_keepsValid() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "feature-src-" + UUID.randomUUID().toString();
        String exc = "feature-exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exc).then().statusCode(lessThan(300));
        String cfg = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", product, cfg).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, cfg, src).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", exc).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, cfg).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_onlyExcludedActive_keepsValid() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "feature-src-" + UUID.randomUUID().toString();
        String exc = "feature-exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exc).then().statusCode(lessThan(300));
        String cfg = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", product, cfg).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, cfg, exc).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", exc).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, cfg).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_responseContainsSourceFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_responseContainsExcludedFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("excludedFeature", excluded).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }
}