package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void deleteFeatureWithActiveConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureWithoutConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "standalone").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureForNonExistentProductReturnsServerError() {
        String productName = "nonexistent-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(500);
    }
}