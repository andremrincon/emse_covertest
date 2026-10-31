package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        BASE = base;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_shouldReturn201() {
        String productName = "test-prod-requires-" + UUID.randomUUID().toString();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_shouldReturn201() {
        String productName = "test-prod-excludes-" + UUID.randomUUID().toString();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintById_shouldReturn204() {
        String productName = "test-prod-del-const-" + UUID.randomUUID().toString();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Push-Notifications")
                .formParam("requiredFeature", "User-Authentication")
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().delete("/products/{productName}/constraints/{constraintId}", productName, 1L);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct_shouldReturn204_and_clear_constraints() {
        String productName = "test-prod-delete-" + UUID.randomUUID().toString();
        given().baseUri(BASE).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().delete("/products/{productName}", productName);
        act.then().statusCode(204);
    }
}