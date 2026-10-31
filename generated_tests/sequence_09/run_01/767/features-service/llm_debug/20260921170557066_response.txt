package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() throws Exception {
        String productName = "req-prod-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturns201() throws Exception {
        String productName = "excl-prod-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() throws Exception {
        String productName = "del-const-prod-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        Response createResp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Push-Notifications")
                .formParam("requiredFeature", "User-Authentication")
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300))
                .extract().response();
        String location = createResp.getHeader("Location");
        String id = location != null && location.contains("/") ? location.substring(location.lastIndexOf('/') + 1) : "0";
        given().when().delete("/products/{productName}/constraints/{constraintId}", productName, id)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns500ForProblematicProduct() throws Exception {
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "12345")
                .formParam("requiredFeature", "Graphics-Card")
                .when().post("/products/{productName}/constraints/requires", "Desktop-PC")
                .then().statusCode(500);
    }
}