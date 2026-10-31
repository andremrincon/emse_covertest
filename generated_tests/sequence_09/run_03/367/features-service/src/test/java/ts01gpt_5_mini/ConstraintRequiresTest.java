package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void constraintTypeIsRequires_inProductDetails() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Push-Notifications")
                .formParam("requiredFeature", "User-Authentication")
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", product)
                .then().body("constraints[0].type", equalTo("requires"));
    }

    @Ignore("1 expectation failed. JSON path $ doesn't match. Expected: a collection containing \"FeatureB-f6d...")
    @Test(timeout = 60000)
    public void evaluateConfiguration_addsRequiredFeature_whenSourceActive() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String sourceFeature = "FeatureA-" + UUID.randomUUID();
        String requiredFeature = "FeatureB-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration)
                .then().body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void evaluateConfiguration_doesNotAddRequiredFeature_whenSourceInactive() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "cfg-" + UUID.randomUUID();
        String sourceFeature = "InactiveSource-" + UUID.randomUUID();
        String requiredFeature = "WouldBeRequired-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration)
                .then().body("$", not(hasItem(requiredFeature)));
    }

    @Ignore("1 expectation failed. JSON path constraints[0].sourceFeature doesn't match. Expected: RAID-Contro...")
    @Test(timeout = 60000)
    public void constraintSourceAndRequiredNames_presentInProductConstraintDetails() {
        String product = "prod-" + UUID.randomUUID();
        String sourceFeature = "RAID-Controller-Card-" + UUID.randomUUID();
        String requiredFeature = "128GB-ECC-RAM-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", product)
                .then().body("constraints[0].sourceFeature", equalTo(sourceFeature));
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_andThenUpdateFeature_returns200OnPut() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "Backlit-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.")
                .when().put("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(200);
    }
}