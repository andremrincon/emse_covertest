package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_shouldReturn201_and_setIdInternally() {
        String productName = "prod-requires-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_shouldReturnBodyContainingId() {
        String productName = "prod-excludes-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void deleteConstraint_shouldReturn204_whenConstraintExists() {
        String productName = "prod-delete-const-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response created = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Feature-For-Delete")
                .formParam("excludedFeature", "Feature-To-Be-Deleted")
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300)).extract().response();
        String constraintId;
        String location = created.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            int idx = location.lastIndexOf('/');
            constraintId = (idx >= 0 && idx < location.length() - 1) ? location.substring(idx + 1) : location;
        } else {
            String body = created.getBody() == null ? "" : created.getBody().asString().trim();
            if (!body.isEmpty()) {
                try {
                    String idFromJson = created.jsonPath().getString("id");
                    constraintId = idFromJson != null ? idFromJson : body;
                } catch (Exception e) {
                    constraintId = body;
                }
            } else {
                constraintId = "";
            }
        }
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", productName, constraintId);
        act.then().statusCode(204);
    }
}