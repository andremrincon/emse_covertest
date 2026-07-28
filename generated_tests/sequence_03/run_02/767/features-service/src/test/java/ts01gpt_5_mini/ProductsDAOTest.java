package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String productName = "test-product-req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_returns201() {
        String productName = "test-product-excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProduct_clearsConstraints_and_returns204() {
        String productName = "test-product-del-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "FeatureA")
                .formParam("requiredFeature", "FeatureB")
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}", productName);
        Assert.assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintById_returns204() {
        String productName = "test-product-constraint-id-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response created = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "SourceX")
                .formParam("excludedFeature", "ExcludedY")
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300)).extract().response();
        String id = null;
        String contentType = created.getHeader("Content-Type");
        if (contentType != null && contentType.toLowerCase().contains("json")) {
            Object idObj = created.jsonPath().get("id");
            id = idObj == null ? created.jsonPath().getString("id") : String.valueOf(idObj);
        } else {
            String location = created.getHeader("Location");
            if (location != null && !location.isEmpty()) {
                int idx = location.lastIndexOf('/');
                id = idx >= 0 ? location.substring(idx + 1) : location;
            } else {
                String body = created.asString();
                if (body != null && !body.trim().isEmpty()) {
                    id = body.trim();
                }
            }
        }
        Assert.assertNotNull(id);
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", productName, id);
        Assert.assertEquals(204, act.getStatusCode());
    }
}