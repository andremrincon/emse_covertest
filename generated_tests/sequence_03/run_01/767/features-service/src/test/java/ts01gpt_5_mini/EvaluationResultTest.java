package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

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
    public void testConfigurationValidFlagIsTrue() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String config = "cfg-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        act.then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String config = "cfg-" + uuid;
        String feature = "feature-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductReturns200() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String feature = "feature-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().statusCode(200);
    }
}