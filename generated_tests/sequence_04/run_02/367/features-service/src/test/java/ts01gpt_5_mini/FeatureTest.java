package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "d").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().get("/products/{productName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "initial").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "updated description").when().put("/products/{productName}/features/{featureName}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "toDelete").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().delete("/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "cfgfeat").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "cfgdel").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).contentType(ContentType.URLENC).formParam("description", "active").when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().get("/products/{productName}/configurations/{configurationName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", configuration).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).when().get("/products/{productName}/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).contentType(ContentType.URLENC).formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("requiredFeature", "req-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).contentType(ContentType.URLENC).formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("excludedFeature", "excl-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/excludes").then().statusCode(201);
    }
}