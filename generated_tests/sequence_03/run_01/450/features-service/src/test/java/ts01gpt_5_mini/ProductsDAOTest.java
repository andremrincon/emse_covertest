package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE");
        BASE = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintCreatesConstraint() {
        String product = "prod-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintCreatesConstraint() {
        String product = "prod-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintByIdReturns204() {
        String product = "prod-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i7")
                .formParam("excludedFeature", "Old-GPU")
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        Response getResp = given().baseUri(BASE).when().get("/products/{productName}", product).then().statusCode(lessThan(300)).extract().response();
        Object idObj = getResp.path("constraints[0].id");
        Number idNum = (idObj instanceof Number) ? (Number) idObj : null;
        long id = (idNum != null) ? idNum.longValue() : -1L;
        given().baseUri(BASE).when().delete("/products/{productName}/constraints/{constraintId}", product, id).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteProductRemovesAllConstraints() {
        String product = "prod-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "FeatureA")
                .formParam("requiredFeature", "FeatureB")
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "FeatureC")
                .formParam("excludedFeature", "FeatureD")
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().delete("/products/{productName}", product).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithoutConstraintsReturns204() {
        String product = "prod-" + UUID.randomUUID();
        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().delete("/products/{productName}", product).then().statusCode(204);
    }
}