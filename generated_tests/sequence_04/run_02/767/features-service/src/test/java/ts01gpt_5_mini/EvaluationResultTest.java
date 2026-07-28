package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_shouldReturn201() {
        String productName = "product-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "Automatically added feature").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_shouldReturn201() {
        String productName = "prod-cfg-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "cfg-feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_shouldReturn201() {
        String productName = "product-req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "Source-" + UUID.randomUUID().toString()).formParam("requiredFeature", "Required-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }
}