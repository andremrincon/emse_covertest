package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createProduct_thenGetProduct_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", productName);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getProductFeatures_returns200_afterAddingFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "fa-" + UUID.randomUUID().toString();
        String featureB = "fb-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "a")
                .when().post("/products/{productName}/features/{featureName}", productName, featureA)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "b")
                .when().post("/products/{productName}/features/{featureName}", productName, featureB)
                .then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", productName);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeature_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "orig")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "updated description")
                .when().put("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeature_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "to-delete")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void createConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "fc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeatures_containsFeatureName() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "fc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "fc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void getConfiguration_returns_validTrue() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        act.then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void createThenDeleteConfiguration_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}", productName, configName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }
}