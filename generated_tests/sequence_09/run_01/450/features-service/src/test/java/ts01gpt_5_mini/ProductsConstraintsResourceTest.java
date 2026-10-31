package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    private static String BASE_URI;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) env = System.getenv("API_BASE");
        BASE_URI = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        RestAssured.baseURI = BASE_URI;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_Success() {
        String productName = "prod-req-" + UUID.randomUUID().toString();
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_Success() {
        String productName = "prod-excl-" + UUID.randomUUID().toString();
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_ServerErrorForProblematicProduct() {
        String triggerProduct = "Desktop-PC";
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";
        String setupProduct = "setup-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", setupProduct).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", setupProduct, "dummy-feature").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", triggerProduct).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_ServerErrorForProblematicProduct() {
        String triggerProduct = "Workstation-Z8";
        String sourceFeature = "PSU-Redundant";
        String excludedFeature = "OS-Home";
        String setupProduct = "setup-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", setupProduct).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", setupProduct, "dummy-feature").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", triggerProduct).then().statusCode(500);
    }
}