package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void corsHeadersPresentOnGetFeatures() {
        given().when().get("/products").then().statusCode(lessThan(300));
        String productName = "AeroBook-Pro-15";
        given().when().get("/products/{productName}/features", productName).then().header("Access-Control-Allow-Headers", "x-requested-with");
    }

    @Test(timeout = 60000)
    public void optionsRequestReturnsAllowedMethodsHeader() {
        String productName = "opt-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().options("/products/{productName}/features", productName).then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void deleteFeatureIncludesMaxAgeHeader() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void nonOptionsRequestsIncludeAllowOriginHeaderOnPostFeature() {
        String product = "p-" + UUID.randomUUID().toString();
        String feature = "f-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().header("Access-Control-Allow-Origin", "*");
    }
}