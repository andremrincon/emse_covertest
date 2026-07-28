package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturnsIdInBody() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName);
        Object id = null;
        String ct = resp.getHeader("Content-Type");
        if (ct != null && ct.contains("application/json")) {
            id = resp.jsonPath().get("id");
        } else {
            String body = resp.getBody().asString();
            if (body != null && !body.trim().isEmpty()) {
                id = body.trim();
            } else {
                String loc = resp.getHeader("Location");
                if (loc != null && !loc.trim().isEmpty()) {
                    String[] parts = loc.split("/");
                    id = parts[parts.length - 1];
                }
            }
        }
        assertNotNull(id);
    }
}