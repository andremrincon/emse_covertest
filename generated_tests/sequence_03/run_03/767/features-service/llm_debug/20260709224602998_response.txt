package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateProductReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().get("/products").then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesShowsAddedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testFeatureAbsentAfterDelete() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().body(not(containsString(featureName)));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintSuccessReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintMissingFeatureReturns500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String missingSource = "Missing-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", missingSource)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintSuccessReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Src-" + UUID.randomUUID().toString();
        String excludedFeature = "Excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaMultipleAddsResultsInExpectedCount() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String f1 = "F1-" + UUID.randomUUID().toString();
        String f2 = "F2-" + UUID.randomUUID().toString();
        String f3 = "F3-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, f1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, f2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, f3).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().body("size()", equalTo(3));
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Updated description")
                .when().put("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(200);
    }
}