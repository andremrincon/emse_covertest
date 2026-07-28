package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response arrangeCreate = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "PSU-Redundant")
                .formParam("excludedFeature", "OS-Home")
                .when().post("/products/{productName}/constraints/excludes", productName);
        arrangeCreate.then().statusCode(lessThan(300));
        String constraintId = null;
        String location = arrangeCreate.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            int idx = location.lastIndexOf('/');
            constraintId = idx >= 0 ? location.substring(idx + 1) : location;
        } else {
            String body = arrangeCreate.asString();
            if (body != null && !body.trim().isEmpty()) {
                constraintId = new JsonPath(body).getString("id");
            }
        }
        if (constraintId == null) {
            constraintId = "";
        }
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", productName, constraintId);
        act.then().statusCode(204);
    }
}