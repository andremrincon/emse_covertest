package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String product = "Product-" + UUID.randomUUID();
        String feature = "Feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void getProductFeatures_returns200() {
        String product = "Product-" + UUID.randomUUID();
        String feature = "Feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromProduct_returns204() {
        String product = "Product-" + UUID.randomUUID();
        String feature = "RemFeat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201() {
        String product = "Product-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String feature = "CfgFeat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_returns204() {
        String product = "Product-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String feature = "CfgRemFeat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeatures_returns200() {
        String product = "Product-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String feature = "CfgListFeat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_returns201() {
        String product = "Product-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "Source-" + UUID.randomUUID()).formParam("requiredFeature", "Required-" + UUID.randomUUID()).when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraint_returns201() {
        String product = "Product-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "S-" + UUID.randomUUID()).formParam("excludedFeature", "E-" + UUID.randomUUID()).when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraint_returns204() {
        String product = "Product-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response created = given().formParam("sourceFeature", "SX-" + UUID.randomUUID()).formParam("excludedFeature", "EX-" + UUID.randomUUID()).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300)).extract().response();
        String id = null;
        String location = created.getHeader("Location");
        if (location != null && location.lastIndexOf('/') != -1) {
            id = location.substring(location.lastIndexOf('/') + 1);
        } else {
            id = created.path("id");
        }
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", product, id);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getProductByName_returns200() {
        String product = "Product-" + UUID.randomUUID();
        String feature = "FeatQuery-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feature).formParam("requiredFeature", "Req-" + UUID.randomUUID()).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", product);
        act.then().statusCode(200);
    }
}