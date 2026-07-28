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
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_WithSourceAndRequired() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_WithOnlyRequiredFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String required = "req-" + UUID.randomUUID().toString();
        given().formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_WithOnlySourceFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", source)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint_WithEmptySourceAndValidRequired() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String required = "req-" + UUID.randomUUID().toString();
        given().formParam("sourceFeature", "").formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(lessThan(300));
    }
}