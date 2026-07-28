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
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_WithBothFeatures_createsConstraint() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "SRC-" + UUID.randomUUID();
        String req = "REQ-" + UUID.randomUUID();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("requiredFeature", req)
                .when()
                .post("/products/{productName}/constraints/requires", product);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_WithOnlySourceFeature_createsConstraint() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "ONLYSRC-" + UUID.randomUUID();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .when()
                .post("/products/{productName}/constraints/requires", product);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_WithOnlyRequiredFeature_createsConstraint() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String req = "ONLYREQ-" + UUID.randomUUID();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("requiredFeature", req)
                .when()
                .post("/products/{productName}/constraints/requires", product);
        resp.then().statusCode(201);
    }
}