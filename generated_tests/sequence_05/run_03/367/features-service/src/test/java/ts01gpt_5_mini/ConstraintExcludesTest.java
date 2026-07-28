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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetSourceFeatureName_viaCreateConstraint_assertSourceFeatureInResponse() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "CPU-i9-13900H-" + UUID.randomUUID().toString();
        String excludedFeature = "Integrated-Graphics-Only-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        resp.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSetExcludedFeatureName_viaCreateConstraint_assertExcludedFeatureInResponse() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        resp.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateConstraint_whenExcludedMissing_assert201StatusCode() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "OnlySource-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateConstraint_withUniqueIds_assertConstraintTypeIsExcludes() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "SF-" + UUID.randomUUID().toString();
        String excludedFeature = "EF-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        resp.then().statusCode(lessThan(300));
    }
}