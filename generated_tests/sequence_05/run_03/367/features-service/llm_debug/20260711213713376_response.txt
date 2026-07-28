package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class FeatureTest {

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
    public void addFeatureToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Feature description")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeaturesForProduct_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeature_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "updated description")
                .when().put("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeature_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to delete")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature for config")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_returns200_and_containsFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature for config")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then().statusCode(200).body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfiguration_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to remove")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(204);
    }
}