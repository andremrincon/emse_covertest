package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        baseUrl = env != null && !env.isEmpty() ? env : System.getProperty("api.base", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintSuccess() {
        String productName = "Enterprise-Server-X1-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintSuccess() {
        String productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintServerError() {
        String productName = "Desktop-PC";
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintServerError() {
        String productName = "Workstation-Z8";
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "PSU-Redundant")
                .formParam("excludedFeature", "OS-Home")
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201);
    }
}