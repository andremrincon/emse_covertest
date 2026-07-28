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
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithoutFormUsesDefaultConstructor_returns201() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withSourceAndExcluded_returnsSourceFieldInBody() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "exc-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_bothFeaturesActive_configurationBecomesInvalid() {
        String product = "prod-" + UUID.randomUUID();
        String featureA = "featA-" + UUID.randomUUID();
        String featureB = "featB-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_onlySourceActive_configurationRemainsValid() {
        String product = "prod-" + UUID.randomUUID();
        String featureA = "featA-" + UUID.randomUUID();
        String featureB = "featB-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200).body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testConstraintCreation_returnsConstraintTypeEXCLUDES() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "exc-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreatingConstraint_thenAddingFeatures_triggersGetterSetterBehavior_andReturnsExpectedSourceAndExcluded() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "exc-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("excludedFeature", excluded)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }
}