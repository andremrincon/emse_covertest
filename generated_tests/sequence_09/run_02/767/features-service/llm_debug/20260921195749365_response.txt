package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DuplicatedObjectExceptionTest {

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        String product = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String feature = "Feature-" + UUID.randomUUID().toString();
        Response resp = given().when().post("/products/{productName}/features/{featureName}", product, feature);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddingSameFeatureTwiceReturns500() {
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        String product = "DupProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String feature = "DupFeature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/features/{featureName}", product, feature);
        resp.then().statusCode(500);
    }
}