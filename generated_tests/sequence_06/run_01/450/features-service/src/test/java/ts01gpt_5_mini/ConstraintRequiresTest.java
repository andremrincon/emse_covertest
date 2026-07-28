package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL", System.getenv("API_BASE_URL") == null ? "http://localhost:8080" : System.getenv("API_BASE_URL"));
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateAddsRequiredFeatureWhenSourceActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, sourceFeature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration)
                .then().body(containsString(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testEvaluateDoesNotAddRequiredWhenSourceInactive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration)
                .then().body(not(containsString(requiredFeature)));
    }
}