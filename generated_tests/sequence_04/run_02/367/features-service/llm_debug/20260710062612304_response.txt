package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("baseUrl", "http://localhost:8080");
        if (base.endsWith("/")) base = base.substring(0, base.length() - 1);
        if (base.startsWith("http://") || base.startsWith("https://")) {
            RestAssured.baseURI = base;
        } else {
            RestAssured.baseURI = "http://" + base;
        }
    }

    @Test(timeout = 60000)
    public void optionsRequestShouldReturnCORSAllowMethodsHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().options("/products/{productName}/features", productName);
        act.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void getFeaturesShouldInvokeChainAndReturn200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void postFeatureShouldSetAccessControlAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void deleteFeatureShouldReturn204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }
}