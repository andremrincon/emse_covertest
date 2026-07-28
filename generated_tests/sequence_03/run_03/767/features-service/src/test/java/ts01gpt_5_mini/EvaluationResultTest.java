package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    static {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddProductCreatesEvaluationResult() {
        given().when().get("/products").then().statusCode(lessThan(300));
        String productName = "product-" + UUID.randomUUID().toString();
        Response act = given().when().post("/products/{productName}", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductProducesCreated() {
        String productName = "product-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddConfigurationAndGetConfigurationReturns200() {
        String productName = "product-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        act.then().statusCode(200);
    }
}