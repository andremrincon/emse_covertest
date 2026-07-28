package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeaturesForProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        RestAssured.given().when().get("/products/{productName}/features", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeatureOfProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("description", "initial").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("description", "updated description").when().put("/products/{productName}/features/{featureName}", product, feature).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("description", "to be deleted").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        RestAssured.given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("description", "cfg feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesReturns200AndContainsFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("description", "cfg feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300));
        RestAssured.given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().statusCode(200).body(containsString(feature));
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        RestAssured.given().when().get("/products/{productName}/configurations", product).then().statusCode(200);
    }
}