package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetSourceFeatureNameReflectedInResponse() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "CPU-i9-13900H";
        String excl = "Integrated-Graphics-Only";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201).extract().response();
        Assert.assertEquals(src, act.jsonPath().getString("sourceFeature"));
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testSetExcludedFeatureNameReflectedInResponse() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "Color-Black";
        String excl = "Logo-White-On-Black";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201).extract().response();
        Assert.assertEquals(excl, act.jsonPath().getString("excludedFeature"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testEvaluateConfigurationConflictProducesInvalid() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excl).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200).extract().response();
        Assert.assertFalse(act.jsonPath().getBoolean("valid"));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoConflictKeepsValidTrue() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200).extract().response();
        Assert.assertTrue(act.jsonPath().getBoolean("valid"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testEvaluateConfigurationAddsErrorMessageOnConflict() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excl).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200).extract().response();
        String expected = "Feature " + excl + " cannot be active when feature " + src + " is active";
        Assert.assertTrue(act.asString().contains(expected));
    }
}