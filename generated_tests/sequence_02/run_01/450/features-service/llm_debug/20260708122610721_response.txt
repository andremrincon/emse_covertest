package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_withBothFeatures() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_withOnlySourceFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", source).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_withOnlyRequiredFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String required = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        given().formParam("requiredFeature", required).when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }
}