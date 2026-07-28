package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithBothParams() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "SRC-" + UUID.randomUUID().toString();
        String req = "REQ-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationAddsDerivedFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, sourceFeature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationDoesNotAddDerivedWhenSourceNotActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().body("$", not(hasItem(requiredFeature)));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlySourceParam() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "SRC-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlyRequiredParam() {
        String product = "prod-" + UUID.randomUUID().toString();
        String req = "REQ-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("requiredFeature", req)
                .when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }
}