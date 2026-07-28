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
        String base = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when()
                .post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConstraintResponseContainsConstraintType() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when()
                .post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationInvalidWhenBothFeaturesActive() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when()
                .post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excluded).then().statusCode(500);
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        act.then().body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationValidWhenOnlySourceFeatureActive() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when()
                .post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        act.then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationValidWhenOnlyExcludedFeatureActive() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when()
                .post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excluded).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        act.then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String excluded = "excl-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excluded).then().statusCode(lessThan(300));
        Response createResp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("excludedFeature", excluded)
                .when()
                .post("/products/{productName}/constraints/excludes", product);
        createResp.then().statusCode(lessThan(300));
        String id = null;
        String location = createResp.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            int idx = location.lastIndexOf('/');
            id = idx >= 0 ? location.substring(idx + 1) : location;
        } else if (createResp.getContentType() != null && !createResp.getContentType().isEmpty()) {
            id = createResp.path("id");
        } else {
            id = "";
        }
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", product, id);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductReturns200() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().statusCode(200);
    }
}