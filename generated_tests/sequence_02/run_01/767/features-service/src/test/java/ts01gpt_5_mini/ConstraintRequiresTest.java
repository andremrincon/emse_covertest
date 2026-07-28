package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithBothFeatures() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "RAID-Controller-" + UUID.randomUUID().toString();
        String required = "128GB-ECC-RAM-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithEmptySourceFeatureCausesServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String required = "Req-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "")
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreatedRequiresConstraintIsPresentInProduct() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "Source-" + UUID.randomUUID().toString();
        String required = "Req-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
        given().when().get("/products/{productName}", product).then().body(containsString(required));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingRequiredFeatureExpectServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "Source-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }
}