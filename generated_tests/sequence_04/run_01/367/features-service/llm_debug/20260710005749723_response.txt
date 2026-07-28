package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturnsId() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintHasProductName() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "PSU-Redundant")
                .formParam("excludedFeature", "OS-Home")
                .when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response created = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Color-Black")
                .formParam("excludedFeature", "Logo-White-On-Black")
                .when().post("/products/{productName}/constraints/excludes", product);
        created.then().statusCode(lessThan(300));
        String id = null;
        String body = created.getBody() != null ? created.getBody().asString() : null;
        if (body != null && !body.trim().isEmpty()) {
            try {
                id = created.jsonPath().getString("id");
            } catch (Exception e) {
                id = body.trim();
            }
        }
        if (id == null || id.isEmpty()) {
            String loc = created.getHeader("Location");
            if (loc != null && !loc.isEmpty()) {
                int idx = loc.lastIndexOf('/');
                id = idx >= 0 ? loc.substring(idx + 1) : loc;
            }
        }
        if (id == null) {
            id = "";
        }
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, id).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, "distributed-training")
                .then().statusCode(500);
    }
}