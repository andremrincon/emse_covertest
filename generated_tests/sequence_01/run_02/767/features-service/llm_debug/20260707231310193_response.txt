package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("API_BASE_URL");
        if (cfg == null || cfg.isEmpty()) cfg = System.getenv("API_BASE_URL");
        if (cfg == null || cfg.isEmpty()) cfg = "http://localhost:8080";
        RestAssured.baseURI = cfg;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withBothFeatures_assert201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "src-" + UUID.randomUUID().toString();
        String req = "req-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withOnlySource_assert201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "only-src-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withOnlyRequired_assert201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String req = "only-req-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }
}