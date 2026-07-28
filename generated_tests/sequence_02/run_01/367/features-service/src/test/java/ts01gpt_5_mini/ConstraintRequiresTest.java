package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithBothFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlySourceFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Push-Notifications")
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlyRequiredFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("requiredFeature", "User-Authentication")
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, resp.getStatusCode());
    }
}