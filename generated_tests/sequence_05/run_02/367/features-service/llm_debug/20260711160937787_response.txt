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
    public static void init() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturns200() {
        String uuid = UUID.randomUUID().toString();
        String productName = "test-product-" + uuid;
        String configurationName = "conf-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductReturns204() {
        String uuid = UUID.randomUUID().toString();
        String productName = "pdel-" + uuid;
        String featureName = "fdel-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }
}