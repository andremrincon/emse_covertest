package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

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
    public void testCreateRequiresConstraintInvokesSetters_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String source = "source-" + UUID.randomUUID().toString();
        String required = "required-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", source)
               .formParam("requiredFeature", required)
               .when()
               .post("/products/{productName}/constraints/requires", product)
               .then()
               .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddingSourceFeatureDerivesRequiredFeature_presentInConfigurationFeatures() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source)
               .formParam("requiredFeature", required)
               .when()
               .post("/products/{productName}/constraints/requires", product)
               .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, source)
               .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration)
               .then().body("$", hasItem(required));
    }

    @Test(timeout = 60000)
    public void testWhenRequiredAlreadyActiveConstraintDoesNotDuplicate_requiredAppearsOnce() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "cfg-" + UUID.randomUUID().toString();
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source)
               .formParam("requiredFeature", required)
               .when()
               .post("/products/{productName}/constraints/requires", product)
               .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, required)
               .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, source)
               .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration)
               .then().body("size()", is(2));
    }
}