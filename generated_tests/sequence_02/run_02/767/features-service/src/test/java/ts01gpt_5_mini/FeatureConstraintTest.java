package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_setsEntityId_andReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "sf-" + UUID.randomUUID().toString();
        String req = "rf-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_setsEntityId_andReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "sf-" + UUID.randomUUID().toString();
        String excl = "ef-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }
}