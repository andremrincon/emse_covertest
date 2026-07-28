package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String product = "test-prod-req-" + UUID.randomUUID().toString();
        String src = "src-feature-" + UUID.randomUUID().toString();
        String req = "req-feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, req).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() {
        String product = "test-prod-excl-" + UUID.randomUUID().toString();
        String src = "src-feature-" + UUID.randomUUID().toString();
        String excl = "excl-feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithBadProductNameReturns500() {
        String badProduct = "product/with/slashes";
        given().formParam("sourceFeature", "some-src").formParam("requiredFeature", "some-req").when().post("/products/{productName}/constraints/requires", badProduct).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithServerErrorScenarioReturns500() {
        String problematicProduct = "Workstation-Z8";
        given().formParam("sourceFeature", "PSU-Redundant").formParam("excludedFeature", "OS-Home").when().post("/products/{productName}/constraints/excludes", problematicProduct).then().statusCode(500);
    }
}