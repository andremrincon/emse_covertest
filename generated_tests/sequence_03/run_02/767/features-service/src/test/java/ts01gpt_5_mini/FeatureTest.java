package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().get("/products/{productName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeatureOfProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "initial").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "updated description").when().put("/products/{productName}/features/{featureName}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "toDelete").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().delete("/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "cfg feature").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "cfgdel").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType("application/x-www-form-urlencoded").formParam("description", "cfglist").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().get("/products/{productName}/configurations/{configurationName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getProductByName_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().get("/products/{productName}").then().statusCode(200);
    }
}