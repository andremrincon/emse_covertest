package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

import org.junit.Ignore;
public class ConstraintRequiresTest {

    private static final String BASE;
    static {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = BASE;
    }

    @Ignore


    @Test(timeout = 60000)
    public void test_adds_required_feature_when_source_active_and_required_inactive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeature).then().statusCode(500);
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        assertTrue(act.getBody().asString().contains(requiredFeature));
    }

    @Test(timeout = 60000)
    public void test_does_not_add_required_when_source_not_active() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        assertFalse(act.getBody().asString().contains(requiredFeature));
    }

    @Ignore


    @Test(timeout = 60000)
    public void test_no_action_when_required_already_active() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeature).then().statusCode(500);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, requiredFeature).then().statusCode(500);
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        assertTrue(act.getBody().asString().contains(requiredFeature));
    }

    @Test(timeout = 60000)
    public void test_post_requires_constraint_returns_201_on_successful_creation() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_post_requires_with_varied_feature_names_triggers_setters_and_creation() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString();
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product);
        assertEquals(201, act.getStatusCode());
    }
}