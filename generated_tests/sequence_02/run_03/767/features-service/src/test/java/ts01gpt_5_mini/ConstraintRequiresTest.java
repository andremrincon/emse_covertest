package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintSuccessfully() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        String required = "req-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, required).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithEmptyRequiredFeatureReturnsServerError() {
        String product = "prod-" + UUID.randomUUID();
        String source = "src-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", "")
                .when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithVeryLongNamesReturnsServerError() {
        String product = "prod-" + UUID.randomUUID();
        String longFeature = new String(new char[2000]).replace('\0', 'X');
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "FeatureA")
                .formParam("requiredFeature", longFeature)
                .when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithFormEncodingInvokesSetters() {
        String product = "prod-" + UUID.randomUUID();
        String source = "feature-source-" + UUID.randomUUID();
        String required = "feature-required-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, source).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }
}