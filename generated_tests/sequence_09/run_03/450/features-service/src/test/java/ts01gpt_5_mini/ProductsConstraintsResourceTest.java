package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductsConstraintsResourceTest {

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
    public void addRequiresConstraint_shouldReturn201_whenProductExists() {
        String productName = "req-prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_locationHeader_shouldContain_constraints_segment() {
        String productName = "req-prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Push-Notifications")
                .formParam("requiredFeature", "User-Authentication")
                .when()
                .post("/products/{productName}/constraints/requires", productName);
        String location = resp.getHeader("Location");
        assertTrue(location != null && location.contains("/products/" + productName + "/constraints/"));
    }

    @Test(timeout = 60000)
    public void addExcludesConstraint_shouldReturn201_whenProductExists() {
        String productName = "excl-prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addExcludesConstraint_locationHeader_shouldContain_singular_constraint_segment() {
        String productName = "excl-prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Color-Black")
                .formParam("excludedFeature", "Logo-White-On-Black")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        String location = resp.getHeader("Location");
        assertTrue(location != null && location.contains("/products/" + productName + "/constraint/"));
    }
}