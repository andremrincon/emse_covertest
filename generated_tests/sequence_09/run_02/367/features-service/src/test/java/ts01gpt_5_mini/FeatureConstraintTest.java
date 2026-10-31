package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv().getOrDefault("API_BASE_URL", System.getProperty("api.base", "http://localhost"));
        String portStr = System.getenv().getOrDefault("API_PORT", System.getProperty("api.port", "8080"));
        RestAssured.baseURI = base;
        try {
            RestAssured.port = Integer.parseInt(portStr);
        } catch (Exception e) {
            RestAssured.port = 80;
        }
    }

    @Test(timeout = 60000)
    public void testCreateProductAndAddRequiresConstraint_thenGetProductShowsConstraintId() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", product);
        act.then().body("constraints[0].id", notNullValue());
    }

    @Test(timeout = 60000)
    public void testCreateProductAndAddExcludesConstraint_thenGetProductShowsConstraintId() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only").when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", product);
        act.then().body("constraints[0].id", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "backlit-keyboard";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Initial desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.").when().put("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "heart-rate-monitor-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }
}