package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_withBothSourceAndExcluded_invokesSetters_andReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "feature-src-" + UUID.randomUUID().toString();
        String excl = "feature-excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_withOnlySourceFeature_invokesSetSourceFeatureName_andReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "feature-src-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_withOnlyExcludedFeature_invokesSetExcludedFeatureName_andReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String excl = "feature-excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void configurationWithConflictingFeatures_andExcludesConstraint_marksConfigurationInvalid() {
        String product = "prod-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("excludedFeature", featureB).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB).then().statusCode(500);
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(false));
    }
}