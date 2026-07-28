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
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_returns201() throws Exception {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraint_responseHasId() throws Exception {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void deleteConstraint_returns204() throws Exception {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response created = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300)).extract().response();
        long id = -1L;
        String location = created.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            int q = location.indexOf('?');
            String path = q >= 0 ? location.substring(0, q) : location;
            int idx = path.lastIndexOf('/');
            String last = idx >= 0 ? path.substring(idx + 1) : path;
            try {
                id = Long.parseLong(last);
            } catch (NumberFormatException e) {
                id = -1L;
            }
        } else {
            String body = created.asString();
            if (body != null && !body.trim().isEmpty()) {
                try {
                    id = Long.parseLong(body.trim());
                } catch (NumberFormatException e) {
                    id = -1L;
                }
            }
        }
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", product, id);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_then_getProduct_returns200() throws Exception {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Feature-A")
                .formParam("requiredFeature", "Feature-B")
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", product);
        act.then().statusCode(200);
    }
}