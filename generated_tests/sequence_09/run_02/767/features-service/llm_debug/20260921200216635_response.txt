package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e = System.getenv("API_BASE");
            RestAssured.baseURI = (e == null || e.isEmpty()) ? "http://localhost:8080" : e;
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void whenSourceActive_requiredIsDerivedInConfigurationFeatures() {
        String product = "Enterprise-Server-X1-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String sourceFeature = "RAID-Controller-Card-" + UUID.randomUUID();
        String requiredFeature = "128GB-ECC-RAM-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void whenSourceNotActive_requiredIsNotDerivedInConfigurationFeatures() {
        String product = "Enterprise-Server-X1-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String sourceFeature = "Push-Notifications-" + UUID.randomUUID();
        String requiredFeature = "User-Authentication-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", not(hasItem(requiredFeature)));
    }

    @Test(timeout = 60000)
    public void addingRequiresConstraint_returns201OnSuccess() {
        String product = "Mobile-App-Basic-" + UUID.randomUUID();
        String sourceFeature = "RAID-Controller-Card-" + UUID.randomUUID();
        String requiredFeature = "128GB-ECC-RAM-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addingFeatureToConfiguration_returns201() {
        String product = "QuantumLeap-AI-Platform-" + UUID.randomUUID();
        String config = "standard-gpu-cluster-" + UUID.randomUUID();
        String feature = "distributed-training-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }
}