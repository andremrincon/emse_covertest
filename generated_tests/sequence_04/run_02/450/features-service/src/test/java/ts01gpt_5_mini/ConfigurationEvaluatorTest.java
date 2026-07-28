package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201_when_no_constraints_present() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_triggers_required_feature_activation_when_requires_constraint_exists() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String required = "req-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, source);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfiguration_returns200_after_adding_feature() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300));

        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void adding_chained_requires_constraints_and_adding_root_feature_returns201_triggers_recursive_evaluation() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String a = "featA-" + UUID.randomUUID();
        String b = "featB-" + UUID.randomUUID();
        String c = "featC-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, a).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, b).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, c).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", a).formParam("requiredFeature", b).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", b).formParam("requiredFeature", c).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));

        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, a);
        act.then().statusCode(201);
    }
}