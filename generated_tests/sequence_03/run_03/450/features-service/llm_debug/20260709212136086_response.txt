package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testAddingFeatureWithRequiresConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", requiredFeature).formParam("description", "required feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).formParam("description", "source feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));

        Response resp = given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", sourceFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}");
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddingFeatureToConfiguration_withoutConstraints_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).formParam("description", "simple feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        Response resp = given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}");
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfiguration_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        Response resp = given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().get("/products/{productName}/configurations/{configurationName}");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintEndpoint_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", sourceFeature).formParam("description", "s feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", requiredFeature).formParam("description", "r feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        Response resp = given().pathParam("productName", productName).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires");
        assertEquals(201, resp.getStatusCode());
    }
}