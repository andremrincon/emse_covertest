package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withBothFeatures_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withOnlySourceFeature_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .when().post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withOnlyRequiredFeature_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }
}