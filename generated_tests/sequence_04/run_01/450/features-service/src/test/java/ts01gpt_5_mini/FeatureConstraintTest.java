package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base != null && !base.isEmpty()) {
            if (base.startsWith("http://") || base.startsWith("https://")) {
                RestAssured.baseURI = base;
            } else {
                RestAssured.baseURI = "http://" + base;
            }
        } else {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProduct_includesConstraintId_afterAddingExcludes() {
        String productName = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Color-Black")
                .formParam("excludedFeature", "Logo-White-On-Black")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}", productName);
        resp.then().body("constraints[0].id", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetProduct_constraintsSize_afterAddingRequires() {
        String productName = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Push-Notifications")
                .formParam("requiredFeature", "User-Authentication")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}", productName);
        resp.then().body("constraints.size()", greaterThan(0));
    }
}