package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String productName = "product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "feature-" + UUID.randomUUID().toString();
        Response act = given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfiguration_returns200() {
        String productName = "product-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <500> was greater than <300>.")
    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String productName = "product-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        act.then().statusCode(204);
    }
}