package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_invokesSetters_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", source).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", required).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_derivedFeatureAdded_whenSourceActiveAndRequiredInactive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", source).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", required).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", source).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().get("/products/{productName}/configurations/{configurationName}/features").then().assertThat().body("$", hasItem(required));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_noDerivedAdded_whenSourceInactive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", source).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", required).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().get("/products/{productName}/configurations/{configurationName}/features").then().assertThat().body("$", not(hasItem(required)));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_noDuplicate_whenRequiredAlreadyActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", source).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", required).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", required).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").pathParam("productName", product).formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", source).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        String jsonPathExpr = String.format("findAll { it == '%s' }.size()", required);
        given().pathParam("productName", product).pathParam("configurationName", config).when().get("/products/{productName}/configurations/{configurationName}/features").then().assertThat().body(jsonPathExpr, equalTo(1));
    }
}