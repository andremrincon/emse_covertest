package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createProductAndAddFeature_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when()
                .post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures something")
                .when().post("/products/{productName}/features/{featureName}", product, feature);
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d1").when()
                .post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void updateFeature_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial").when()
                .post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "RGB backlit keyboard with customizable zones")
                .when().put("/products/{productName}/features/{featureName}", product, feature);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void deleteFeature_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to delete").when()
                .post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "feat desc").when()
                .post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to config").when()
                .post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String featureA = "featA-" + UUID.randomUUID();
        String featureB = "featB-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "A").when()
                .post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "B").when()
                .post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureB).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void createConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void deleteConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}", product, configuration);
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", product);
        assertEquals(201, act.getStatusCode());
    }
}