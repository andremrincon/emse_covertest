package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testAddFeature() {
        String productName = "Product_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature() {
        String productName = "Product_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(lessThan(300));
        given().when().delete(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Product_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature2").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "Feature1").formParam("requiredFeature", "Feature2").when().post(BASE_URL + "/products/" + productName + "/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Product_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature2").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "Feature1").formParam("excludedFeature", "Feature2").when().post(BASE_URL + "/products/" + productName + "/constraints/excludes").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features/Feature1").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamed() {
        String productName = "Product_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/Feature1").then().statusCode(500);
    }
}