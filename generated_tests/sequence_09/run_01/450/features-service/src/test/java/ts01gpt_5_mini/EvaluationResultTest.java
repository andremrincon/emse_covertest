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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureReturns201() {
        String uuid = UUID.randomUUID().toString();
        String productName = "test-product-" + uuid;
        String featureName = "feature-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().formParam("description", "Measures the oxygen saturation (SpO2) on demand.")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeaturesForProductReturns200() {
        String uuid = UUID.randomUUID().toString();
        String productName = "test-product-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "Demo feature")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureReturns204() {
        String uuid = UUID.randomUUID().toString();
        String productName = "test-product-" + uuid;
        String featureName = "delfeat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "To be deleted")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        resp.then().statusCode(204);
    }
}