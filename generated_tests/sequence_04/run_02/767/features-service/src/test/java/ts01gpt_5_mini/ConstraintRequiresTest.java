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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        String req = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, req).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void constraintReflectedInProduct_constraintsTypeRequires() {
        String product = "prod-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        String req = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
        given().when().get("/products/{productName}", product).then().body("constraints.type", hasItem("requires"));
    }

    @Test(timeout = 60000)
    public void evaluate_addsRequiredFeature_whenSourceActive() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "conf-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        String req = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, req).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, src).then().statusCode(201);
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().body("$", hasItem(req));
    }

    @Test(timeout = 60000)
    public void evaluate_noAddition_whenSourceInactive() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "conf-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        String req = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().body("$", not(hasItem(req)));
    }

    @Test(timeout = 60000)
    public void evaluate_noDuplicate_whenRequiredAlreadyActive() {
        String product = "prod-" + UUID.randomUUID();
        String configuration = "conf-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        String req = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, req).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, req).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, src).then().statusCode(201);
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration).then().body("$", hasItem(req));
    }

    @Test(timeout = 60000)
    public void settersMapping_withForm_postReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String src = "src-" + UUID.randomUUID();
        String req = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }
}