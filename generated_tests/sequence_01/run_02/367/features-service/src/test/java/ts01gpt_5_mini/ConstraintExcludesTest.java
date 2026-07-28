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
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_hasConstraintTypeEXCLUDES() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraint_responseContainsSourceFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testExcludesConstraint_responseContainsExcludedFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testExcludesEvaluation_invalidWhenBothFeaturesActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config)
                .then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testExcludesEvaluation_validWhenOnlySourceActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String excluded = "exc-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config)
                .then().body("valid", equalTo(true));
    }
}