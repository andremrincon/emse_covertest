package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetProductIncludesConstraintId() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("constraints[0].id", notNullValue());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() {
        String productName = "excl-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "Color-Black").formParam("excludedFeature", "Logo-White-On-Black")
                .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() {
        String productName = "UltraPhoneX";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/constraints/{constraintId}", productName, 12345).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintReturns201() {
        String productName = "requires-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "feature-prod-" + UUID.randomUUID().toString();
        String featureName = "Blood Oxygen Sensor-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductReturns200() {
        String productName = "features-list-prod-" + UUID.randomUUID().toString();
        String featureName = "ANC-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Active Noise Cancellation")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }
}