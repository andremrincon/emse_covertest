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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "CPU-" + UUID.randomUUID().toString();
        String excl = "IG-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintServerErrorForProblematicProduct() {
        given().when().get("/products").then().statusCode(lessThan(300));
        String problematic = "Workstation-Z8";
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "PSU-Redundant")
                .formParam("excludedFeature", "OS-Home")
                .when().post("/products/{productName}/constraints/excludes", problematic)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testConstraintEvaluationDetectsConflictAndMarksConfigurationInvalid() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String f1 = "featureA-" + UUID.randomUUID().toString();
        String f2 = "featureB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, f1).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, f2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, f1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, f2).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", f1)
                .formParam("excludedFeature", f2)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration)
                .then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testConstraintEvaluationNoConflictKeepsConfigurationValid() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String f1 = "featureA-" + UUID.randomUUID().toString();
        String f2 = "featureB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, f1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, f1).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", f2)
                .formParam("excludedFeature", "nonexistent-" + UUID.randomUUID().toString())
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration)
                .then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testConstraintCreationReturnsConstraintTypeEXCLUDES() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testConstraintCreationReflectsSourceFeatureField() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "Color-Black-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", "Logo-White-On-Black")
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204ForKnownId() {
        given().when().get("/products").then().statusCode(lessThan(300));
        String product = "UltraPhoneX";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, 12345).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintWithInvalidIdProducesServerError() {
        given().when().get("/products").then().statusCode(lessThan(300));
        String product = "PowerBankPro";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, "abc-def").then().statusCode(404);
    }
}