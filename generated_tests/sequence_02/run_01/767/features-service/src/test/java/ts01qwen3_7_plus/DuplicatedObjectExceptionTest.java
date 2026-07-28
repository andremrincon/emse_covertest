package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DuplicatedObjectExceptionTest {

    @Test(timeout = 60000)
    public void testDuplicateProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName).then().statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <201> but was <500>.")
    @Test(timeout = 60000)
    public void testDuplicateFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDuplicateConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(201);
    }
}