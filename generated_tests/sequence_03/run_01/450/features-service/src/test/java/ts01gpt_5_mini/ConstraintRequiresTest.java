package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        Response act = given().when().post("/products/{productName}", productName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        Response act = given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testConstraintReflectedInProduct_getTypeRequires() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", productName);
        String body = act.asString();
        assertTrue(body.contains("\"type\":\"requires\"") || body.contains("requires"));
    }

    @Test(timeout = 60000)
    public void testAddSourceFeatureToConfiguration_returns201_triggers_evaluation() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, required).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, config).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, config, source);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_derivesRequiredFeature_presentInConfigurationFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, required).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, config, source).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, config);
        String body = act.asString();
        assertTrue(body.contains(required));
    }

    @Test(timeout = 60000)
    public void testWhenRequiredAlreadyActive_configurationHasTwoFeatures_noDuplicates() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, required).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, config, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, config, source).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, config);
        int size = act.jsonPath().getList("$").size();
        assertEquals(2, size);
    }
}