package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "Auto-generated feature").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "Explicit add").when().post("/products/{productName}/features/{featureName}", productName, featureName + "-new");
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "remove-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "to be removed").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_containsAddedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "visible-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "visible feature").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", productName);
        act.then().statusCode(200).body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_caseInsensitive_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String originalFeature = "Heart-Rate-Monitor";
        String updatePathFeature = "heart-rate-monitor";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "original").when().post("/products/{productName}/features/{featureName}", productName, originalFeature).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "updated desc").when().put("/products/{productName}/features/{featureName}", productName, updatePathFeature);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateNonexistentFeature_returns500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String missingFeature = "nonexistent-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "no such feature").when().put("/products/{productName}/features/{featureName}", productName, missingFeature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "source").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "required").when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "srcEx-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "src").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "excl").when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraint_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "srcDel-" + UUID.randomUUID().toString();
        String excludedFeature = "exclDel-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "src").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "excl").when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        Response createResp = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName);
        String location = createResp.then().statusCode(lessThan(300)).extract().header("Location");
        String constraintId;
        if (location != null) {
            int idx = location.lastIndexOf('/');
            constraintId = idx >= 0 ? location.substring(idx + 1) : location;
        } else {
            constraintId = createResp.then().extract().path("id");
        }
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", productName, constraintId);
        act.then().statusCode(204);
    }
}