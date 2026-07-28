package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenProductHasFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        String featureA = "featureA-" + UUID.randomUUID().toString();
        String featureB = "featureB-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureA).formParam("description", "descA").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureB).formParam("description", "descB").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        Response resp = given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().get("/products/{productName}/configurations/{configurationName}");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenProductHasNoFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        Response resp = given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().get("/products/{productName}/configurations/{configurationName}");
        resp.then().statusCode(200);
    }
}