package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withBothFeatures_shouldReturn201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RequestSpecification req = given().contentType("application/x-www-form-urlencoded");
        req = req.formParam("sourceFeature", "CPU-i9-13900H")
                 .formParam("excludedFeature", "Integrated-Graphics-Only");
        Response response = req.when().post("/products/{productName}/constraints/excludes", productName);
        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlySourceFeature_shouldReturn201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RequestSpecification req = given().contentType("application/x-www-form-urlencoded");
        req = req.formParam("sourceFeature", "RAID-Controller-Card");
        Response response = req.when().post("/products/{productName}/constraints/excludes", productName);
        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlyExcludedFeature_shouldReturn201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RequestSpecification req = given().contentType("application/x-www-form-urlencoded");
        req = req.formParam("excludedFeature", "Integrated-Graphics-Only");
        Response response = req.when().post("/products/{productName}/constraints/excludes", productName);
        response.then().statusCode(201);
    }
}