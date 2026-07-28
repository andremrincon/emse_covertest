package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createProductThenAddFeatureReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .andReturn();
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createProductThenAddFeatureWithDescriptionReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-desc-" + UUID.randomUUID().toString();
        String description = "Measures the oxygen saturation (SpO2) of your blood on demand.";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .andReturn();
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createProductAndFeatureThenUpdateFeatureReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-update-" + UUID.randomUUID().toString();
        String newDescription = "RGB backlit keyboard with customizable zones and per-key lighting.";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", newDescription)
                .when().put("/products/{productName}/features/{featureName}", product, feature)
                .andReturn();
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void createProductAndFeatureThenGetFeaturesReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-list-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product).andReturn();
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void createProductAndFeatureThenDeleteFeatureReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-del-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature).andReturn();
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void createProductCreateConfigurationAddFeatureToConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feature-cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).andReturn();
        act.then().statusCode(201);
    }
}