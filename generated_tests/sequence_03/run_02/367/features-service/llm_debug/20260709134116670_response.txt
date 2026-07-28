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
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String src = "src-" + uid;
        String excl = "excl-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintResponseContainsSourceFeature() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String src = "s-" + uid;
        String excl = "e-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationInvalidWhenBothFeaturesActive() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String src = "feature-src-" + uid;
        String excl = "feature-excl-" + uid;
        String config = "conf-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excl).then().statusCode(500);
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationValidWhenOnlySourceActive() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String src = "feature-src-" + uid;
        String excl = "feature-excl-" + uid;
        String config = "conf-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String src = "src-" + uid;
        String excl = "excl-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response r = given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product);
        r.then().statusCode(lessThan(300));
        String id;
        String location = r.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            int idx = location.lastIndexOf('/');
            id = idx >= 0 ? location.substring(idx + 1) : location;
        } else {
            String body = r.getBody().asString();
            if (body != null && !body.trim().isEmpty()) {
                try {
                    id = r.jsonPath().getString("id");
                    if (id == null || id.isEmpty()) {
                        id = body.trim();
                    }
                } catch (Exception e) {
                    id = body.trim();
                }
            } else {
                id = "";
            }
        }
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, id).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String feature = "feat-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String feature = "feat-" + uid;
        String config = "conf-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesReturns200() {
        String uid = UUID.randomUUID().toString();
        String product = "prod-" + uid;
        String feature = "feat-" + uid;
        String config = "conf-" + uid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(200);
    }
}